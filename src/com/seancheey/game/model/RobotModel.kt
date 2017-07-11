package com.seancheey.game.model

import com.seancheey.game.*
import com.seancheey.resources.Resources
import javafx.scene.image.Image
import javafx.scene.image.PixelWriter
import javafx.scene.image.WritableImage


/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
/**
 * Designed as an immutable class as robot model
 */
@Suppress("VAL_REASSIGNMENT_VIA_BACKING_FIELD")
open class RobotModel(var name: String, val components: List<ComponentNode>) : Model {
    override final val actionTree: ActionTree = ActionTree(Action.MOVE_ACTION to Action.moveAction())
    override val width: Double
        get() = Config.botPixelSize
    override val height: Double
        get() = Config.botPixelSize
    @Suppress("SENSELESS_COMPARISON")
    @Transient final
    override val image: Image = immutableImage()
        get() {
            if (field == null) {
                field = immutableImage()
            }
            return field
        }
    @Suppress("SENSELESS_COMPARISON")
    @Transient
    val idleImage: Image = idleImage()
        get() {
            if (field == null) {
                field = idleImage()
            }
            return field
        }
    val price: Int
    val maxSpeed: Double
    val maxAcceleration: Double
    val turnSpeed: Double
    val health: Int
    val weight: Int
    val empty: Boolean
        get() = components.isEmpty()
    val valid: Boolean
        get() = _valid
    private var _valid: Boolean = false
        get() {
            if (!field) {
                verify()
            }
            return field
        }

    init {
        val movementModels = components.filter { it.type == ComponentType.movement }.map { it.getModel<MovementModel>()!! }
        val allModels = components.map { it.model }

        health = allModels.sumBy { it.health }
        weight = allModels.sumBy { it.weight }
        price = allModels.sumBy { it.price }
        maxSpeed = movementModels.sumByDouble { it.force }
        maxAcceleration = movementModels.sumByDouble { it.force } / 20
        turnSpeed = movementModels.sumByDouble { it.turn }
    }

    constructor() : this("", arrayListOf())

    private fun immutableImage(): WritableImage {
        val writeImage = WritableImage(Config.botPixelSize.toInt(), Config.botPixelSize.toInt())
        val writer = writeImage.pixelWriter
        components
                .filter { it.type != ComponentType.weapon }
                .forEach { writer.setPixels(it) }
        return writeImage
    }

    /**
     * write a image to pixel writer
     */
    private fun PixelWriter.setPixels(node: ComponentNode) {
        val x = node.leftX.toInt()
        val y = node.upperY.toInt()
        val image = node.image
        val reader = image.pixelReader
        for (readY in 0 until image.height.toInt()) {
            for (readX in 0 until image.width.toInt()) {
                val color = reader.getColor(readX, readY)
                if (color.isOpaque) {
                    val xPos = x + readX
                    val yPos = y + readY
                    setColor(xPos, yPos, color)
                }
            }
        }
    }

    private fun idleImage(): Image {
        // return "no robot" image if there is no components in robot
        if (components.isEmpty()) {
            return Image(Resources.noRobotImageInStream)
        }
        // add all moving mutableNodes to immutableImage
        val writableImage = immutableImage()
        val writer = writableImage.pixelWriter
        components.forEach { writer.setPixels(it) }

        return writableImage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RobotModel) return false

        if (name != other.name) return false
        if (components != other.components) return false
        if (maxSpeed != other.maxSpeed) return false
        if (maxAcceleration != other.maxAcceleration) return false
        if (turnSpeed != other.turnSpeed) return false
        if (health != other.health) return false
        if (weight != other.weight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + components.hashCode()
        result = 31 * result + maxSpeed.hashCode()
        result = 31 * result + maxAcceleration.hashCode()
        result = 31 * result + turnSpeed.hashCode()
        result = 31 * result + health
        result = 31 * result + weight
        return result
    }

    fun verify(): List<WrongMessage> {
        return Verifier().verify()
    }

    data class Point(val x: Int, val y: Int)
    data class WrongMessage(val message: String, val points: ArrayList<Point> = arrayListOf())

    private inner class Verifier {
        private val verifyList: ArrayList<() -> WrongMessage?> = arrayListOf()

        init {
            verifyList.add { verifyMovements() }
            verifyList.add { verifyOverlap() }
            verifyList.add { verifyConnection() }
            verifyList.add { verifyWeaponConnection() }
        }

        fun verifyWeaponConnection(): WrongMessage? {
            val wrongPoints = arrayListOf<Point>()
            components.filter { it.type == ComponentType.weapon }.forEach { weapon ->
                val hasMount: Boolean = components.filter { Attribute.weapon_mount in it.model.attributes }.filter { it.gridX == weapon.gridX }.any { mount ->
                    mount.gridY - weapon.gridY == (weapon.model.gridHeight - mount.model.gridHeight) / 2
                }
                if (!hasMount) {
                    wrongPoints.addAll(weapon.allPoints())
                }
            }
            if (wrongPoints.isEmpty()) {
                return null
            } else {
                return WrongMessage("Weapon not connected to a mount", wrongPoints)
            }
        }

        fun verifyMovements(): WrongMessage? {
            if (components.isEmpty()) return null
            if (components.any { it.type == ComponentType.movement }) return null else return WrongMessage("The robot can't move! put some movement components")
        }

        fun verifyOverlap(): WrongMessage? {
            val overlapPoints: ArrayList<Point> = arrayListOf()
            val pointMap: HashMap<Point, ComponentNode> = hashMapOf()
            for (comp in components) {
                // record all point that the component has
                val points: ArrayList<Point> = arrayListOf()
                for (y in comp.gridY until comp.gridY + comp.model.gridHeight) {
                    (comp.gridX until comp.gridX + comp.model.gridWidth).mapTo(points) { x -> Point(x, y) }
                }
                for (point in points) {
                    if (pointMap.containsKey(point)) {
                        // except for weapon & mount
                        if (comp.model.attributes.contains(Attribute.weapon_mount) && pointMap[point]!!.model is WeaponModel) {
                            continue
                        }
                        if (pointMap[point]!!.model.attributes.contains(Attribute.weapon_mount) && comp.model is WeaponModel) {
                            continue
                        }
                        if (!overlapPoints.contains(point))
                            overlapPoints.add(point)
                    } else {
                        pointMap.put(point, comp)
                    }
                }
            }
            if (overlapPoints.isEmpty()) return null else return WrongMessage("Overlapped components found", overlapPoints)
        }

        fun verifyConnection(): WrongMessage? {
            if (components.size <= 1) {
                return null
            }
            val disconnectedPoints = arrayListOf<Point>()
            var remainingNode = components.map { it }
            val connectionGroup = arrayListOf<List<ComponentNode>>()
            while (remainingNode.isNotEmpty()) {
                val newGroup = allConnectedComponents(remainingNode[0])
                connectionGroup.add(newGroup)
                remainingNode = remainingNode.filter { it !in newGroup }
            }
            val largestGroup = connectionGroup.maxBy { it.size }
            connectionGroup.filter { it != largestGroup }.forEach {
                for (comp in it) disconnectedPoints.addAll(comp.allPoints())
            }

            if (disconnectedPoints.isEmpty()) return null else return WrongMessage("Disconnect component found", disconnectedPoints)
        }

        fun allConnectedComponents(me: ComponentNode, connectedList: ArrayList<ComponentNode> = arrayListOf(me)): ArrayList<ComponentNode> {
            var conList = connectedList
            val newNodes = arrayListOf<ComponentNode>()
            for (other in components.filter { it != me && it !in conList }) {
                if (other.adjacentWith(me)) {
                    conList.add(other)
                    newNodes.add(other)
                }
            }
            for (node in newNodes) {
                conList = allConnectedComponents(node, conList)
            }
            return conList
        }

        fun ComponentNode.adjacentWith(other: ComponentNode): Boolean {
            val myList = allPoints()
            val otherList = other.allPoints()
            for ((x1, y1) in myList) {
                for ((x2, y2) in otherList) {
                    if (x1 == x2) {
                        if (y1 - y2 <= 1 && y2 - y1 <= 1) {
                            return true
                        }
                    } else if (y1 == y2) {
                        if (x1 - x2 <= 1 && x2 - x1 <= 1) {
                            return true
                        }
                    }
                }
            }
            return false
        }

        fun ComponentNode.allPoints(): ArrayList<Point> {
            val pointList = arrayListOf<Point>()
            for (y in gridY until gridY + model.gridHeight) {
                for (x in gridX until gridX + model.gridWidth) {
                    pointList.add(Point(x, y))
                }
            }
            return pointList
        }


        fun verify(): List<WrongMessage> {
            val messages = verifyList.mapNotNull { it() }
            _valid = messages.isEmpty()
            return messages
        }
    }
}
