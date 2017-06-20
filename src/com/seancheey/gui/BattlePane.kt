package com.seancheey.gui

import com.seancheey.game.*
import com.seancheey.game.battlefield.Battlefield
import javafx.animation.AnimationTimer
import javafx.concurrent.Task
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.transform.Affine

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(override val battlefield: Battlefield, val clipWidth: Double, val clipHeight: Double) : Canvas(battlefield.width, battlefield.height), GameInspector {
    override fun clickRobot(model: RobotModel) {
        if (!model.empty) {
            battlefield.putRobot(model, battlefield.width / 2, battlefield.height / 2, Math.random() * 6)
        }
    }

    override val focusedNodes: ArrayList<Node> = arrayListOf()
    override var cameraTransX: Double = 0.0
        set(value) {
            field = value
            translateX = value
            clipCanvas()
        }
    override var cameraTransY: Double = 0.0
        set(value) {
            field = value
            translateY = value
            clipCanvas()
        }
    override var cameraScale: Double = 1.0
        set(value) {
            scaleX = value
            scaleY = value
            scaleZ = value
            field = value
            clipCanvas()
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
    private var vx: Double = 0.0
    private var vy: Double = 0.0

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
            // request focus to handle key event
            requestFocus()
            cameraTransX += vx
            cameraTransY += vy
        }

        clipCanvas()
        fullMapScale()


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
        }

        setOnKeyPressed { event ->
            when (event.code) {
                KeyCode.W ->
                    vy = 1.0
                KeyCode.S ->
                    vy = -1.0
                KeyCode.A ->
                    vx = 1.0
                KeyCode.D ->
                    vx = -1.0
                else -> return@setOnKeyPressed
            }
        }

        setOnKeyReleased { event ->
            when (event.code) {
                KeyCode.A, KeyCode.D ->
                    vx = 0.0
                KeyCode.W, KeyCode.S ->
                    vy = 0.0
                else -> return@setOnKeyReleased
            }
        }
        start()
    }


    fun fullMapScale() {
        cameraScale = minOf(clipWidth / width, clipHeight / height)
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

    /**
     * ensure the canvas doesn't come out
     */
    private fun clipCanvas() {

        val clipRect = Rectangle((width - clipWidth / cameraScale) / 2 - translateX / cameraScale, (height - clipHeight / cameraScale) / 2 - translateY / cameraScale, clipWidth / cameraScale, clipHeight / cameraScale)
        clip = clipRect
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
        node.children.forEach {
            drawNode(it, newTrans.clone())
        }
    }

    private fun drawFocus(node: Node) {
        graphicsContext2D.transform = Affine()
        graphicsContext2D.strokeOval(node.leftX, node.upperY, node.width, node.height)
    }
}