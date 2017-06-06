package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.GameDirector
import com.seancheey.game.GameInspector
import com.seancheey.game.Node
import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.command.MoveCommand
import javafx.animation.AnimationTimer
import javafx.concurrent.Task
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.scene.transform.Rotate
import javafx.scene.transform.Transform

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(val battlefield: Battlefield, width: Double, height: Double) : Canvas(width, height), GameInspector {
    override var focusedNode: Node? = null
    override var transX: Double = 0.0
    override var transY: Double = 0.0
    override var scale: Double = 1.0
    override val guiWidth: Double
        get() = width
    override val guiHeight: Double
        get() = height
    override val gameDirector: GameDirector = GameDirector(battlefield.nodes)
    val gc: GraphicsContext = graphicsContext2D
    val renderTimer: AnimationTimer

    init {
        gameDirector.render = { lag ->
            gc.fill = Color.LIGHTGRAY
            gc.fillRect(0.0, 0.0, width, height)
            for (node in battlefield.nodes) drawNode(gc, node, lag)
        }
        style = "-fx-background-color: darkgrey;"
        renderTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                gameDirector.render(0.0)
            }
        }
        setOnMouseClicked { event ->
            // TODO delete this test case
            if (focusedNode == null && gameDirector.nodes.size != 0) {
                focusedNode = gameDirector.nodes[0]
            }
            if (focusedNode != null) {
                gameDirector.command(MoveCommand(Config.player, focusedNode!!, event.x, event.y))
            }
        }
        start()
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

    private fun drawNode(gc: GraphicsContext, node: Node, lag: Double) {
        val posX = node.x + node.vx * lag
        val posY = node.y + node.vy * lag
        val rotate: Rotate = Transform.rotate(node.orientation * 180 / Math.PI + 90, posX, posY)
        gc.setTransform(rotate.mxx, rotate.myx, rotate.mxy, rotate.myy, rotate.tx, rotate.ty)
        gc.drawImage(node.image, posX - Config.botSize / 2, posY - Config.botSize / 2, Config.botSize, Config.botSize)
    }
}