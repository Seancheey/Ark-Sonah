package com.seancheey.gui

import com.seancheey.game.*
import com.seancheey.game.battlefield.Battlefield
import javafx.animation.AnimationTimer
import javafx.concurrent.Task
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseButton
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Affine

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(override val battlefield: Battlefield, width: Double, height: Double, startScale: Double = 1.0) : Canvas(width, height), GameInspector {
    override fun clickRobot(model: RobotModel) {
        if (!model.empty) {
            battlefield.putRobot(model, 150.0 + Math.random() * 50, 200.0 + Math.random() * 30, Math.random(), Math.random() * 6)
        }
    }

    override val focusedNodes: ArrayList<Node> = arrayListOf()
    override var cameraTransX: Double = 0.0
        set(value) {
            field = value
            translateX = value
        }
    override var cameraTransY: Double = 0.0
        set(value) {
            field = value
            translateY = value
        }
    override var cameraScale: Double = 1.0
        set(value) {
            scaleX = value
            scaleY = value
            scaleZ = value
            field = value
        }
    override val guiWidth: Double
        get() = width
    override val guiHeight: Double
        get() = height
    override val gameDirector: GameDirector = GameDirector(battlefield.nodes)
    private val renderTimer: AnimationTimer = object : AnimationTimer() {
        override fun handle(now: Long) {
            gameDirector.render(0.0)
        }
    }

    init {
        // set the background of battle field to light gray
        graphicsContext2D.fill = Color.LIGHTGRAY

        // render method
        gameDirector.render = {
            graphicsContext2D.fillRect(0.0, 0.0, this.width, this.height)
            graphicsContext2D.save()
            graphicsContext2D.scale(cameraScale, cameraScale)
            battlefield.nodes.forEach { drawNode(it) }
            focusedNodes.forEach { drawFocus(it) }
            graphicsContext2D.restore()
        }

        setOnMouseClicked { event ->
            if (event.button == MouseButton.PRIMARY) {
                selectRobotBeside(event.x, event.y)
            }
            if (event.button == MouseButton.SECONDARY) {
                moveFocusedRobotsTo(event.x, event.y)
            }
            if (event.button == MouseButton.MIDDLE) {
                val nodes = gameDirector.nodes.filter { it.containsPoint(event.x, event.y) }.filterIsInstance<RobotModel>()
                if (nodes.isNotEmpty()) {
                    val firstNode: RobotModel = nodes[0]
                    selectAllRobotsWithSameType(firstNode)
                }
            }
        }

        setOnScroll { event ->
            if (event.deltaY > 0) {
                cameraScale *= 1 - Config.scrollSpeedDelta
            } else if (event.deltaY < 0) {
                cameraScale *= 1 + Config.scrollSpeedDelta
            }
            //ensure the canvas doesn't come out
            val maxWidth = maxAllowedWidth()
            val maxHeight = maxAllowedHeight()
            val clipRect = Rectangle((width - maxWidth / cameraScale) / 2, (height - maxHeight / cameraScale) / 2, maxWidth / cameraScale, maxHeight / cameraScale)
            clip = clipRect
        }
        start()
    }

    private fun maxAllowedWidth(): Double {
        if (parent is Region) {
            return (parent as Region).width
        } else {
            return width
        }
    }

    private fun maxAllowedHeight(): Double {
        if (parent is Region) {
            return (parent as Region).height
        } else {
            return height
        }
    }

    fun moveCamera(x: Int, y: Int) {
        cameraTransX += x
        cameraTransY += y
    }

    fun start() {
        if (!gameDirector.started) {
            Thread(object : Task<Unit>() {
                override fun call() {
                    gameDirector.start()
                }
            }).start()
            renderTimer.start()
        }
    }

    fun stop() {
        gameDirector.stop = true
        renderTimer.stop()
    }

    private fun nodeTranslation(node: Node): Affine {
        return Affine(Affine.translate(node.leftX, node.upperY))
    }

    private fun nodeRotation(node: Node): Affine {
        if (node is RobotNode) {
            return Affine(Affine.rotate(node.degreeOrientation + 90.0, node.width / 2, node.height / 2))
        } else {
            return Affine(Affine.rotate(node.degreeOrientation, node.width / 2, node.height / 2))
        }
    }

    private fun drawNode(node: Node, transform: Affine = Affine()) {
        val newTrans: Affine = transform
        newTrans.append(nodeTranslation(node))
        newTrans.append(nodeRotation(node))
        graphicsContext2D.transform = newTrans
        graphicsContext2D.drawImage(node.image, 0.0, 0.0, node.width, node.height)
        // recursively draw its children
        node.children.forEach { drawNode(it, newTrans) }
    }

    private fun drawFocus(node: Node) {
        graphicsContext2D.transform = Affine()
        graphicsContext2D.strokeOval(node.leftX, node.upperY, node.width, node.height)
    }
}