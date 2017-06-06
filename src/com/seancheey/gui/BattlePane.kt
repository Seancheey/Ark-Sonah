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
import javafx.scene.input.MouseButton
import javafx.scene.paint.Color
import javafx.scene.transform.Rotate
import javafx.scene.transform.Transform

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(val battlefield: Battlefield, width: Double, height: Double) : Canvas(width, height), GameInspector {
    override fun selectFocusingRobotsAt(x: Double, y: Double) {
        focusedNodes.clear()
        gameDirector.nodes
                .filter { it.containsPoint(x, y) }
                .forEach { focusedNodes.add(it) }
    }

    override fun moveFocusedRobotsTo(x: Double, y: Double) {
        focusedNodes.forEach { node -> gameDirector.command(MoveCommand(Config.player, node, x, y)) }
    }

    override var focusedNodes: ArrayList<Node> = arrayListOf()
    override var transX: Double = 0.0
    override var transY: Double = 0.0
    override var scale: Double = 1.0
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
        gameDirector.render = { lag ->
            graphicsContext2D.fillRect(0.0, 0.0, this.width, this.height)
            graphicsContext2D.scale(scale, scale)
            for (node in battlefield.nodes) drawNode(node, lag)
        }

        setOnMouseClicked { event ->
            if (event.button == MouseButton.PRIMARY) {
                selectFocusingRobotsAt(event.x, event.y)
            }
            if (event.button == MouseButton.SECONDARY) {
                moveFocusedRobotsTo(event.x, event.y)
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

    private fun drawNode(node: Node, lag: Double) {
        val posX = node.x + node.vx * lag
        val posY = node.y + node.vy * lag
        val rotate: Rotate = Transform.rotate(node.orientation * 180 / Math.PI + 90, posX, posY)
        graphicsContext2D.setTransform(rotate.mxx, rotate.myx, rotate.mxy, rotate.myy, rotate.tx, rotate.ty)
        graphicsContext2D.drawImage(node.image, posX - Config.botSize / 2, posY - Config.botSize / 2, Config.botSize, Config.botSize)
    }
}