package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.GameDirector
import com.seancheey.game.GameInspector
import com.seancheey.game.battlefield.Battlefield
import javafx.animation.AnimationTimer
import javafx.concurrent.Task
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(val battlefield: Battlefield, width: Double, height: Double) : Canvas(width, height), GameInspector {
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
            gc.fillRect(0.0, 0.0, width, height)
            for (node in battlefield.nodes) {
                gc.drawImage(node.image, node.x + node.vx * lag, node.y + node.vy * lag, Config.botSize, Config.botSize)
            }
        }
        gameDirector.inputs = {
            //accept user inputs
        }
        style = "-fx-background-color: darkgrey;"
        renderTimer = object : AnimationTimer() {
            override fun handle(now: Long) {
                gameDirector.render(0.0)
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
}