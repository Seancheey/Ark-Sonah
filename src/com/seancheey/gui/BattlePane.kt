package com.seancheey.gui

import com.seancheey.game.BattleField
import com.seancheey.game.GameDirector
import javafx.scene.canvas.Canvas

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattlePane(val battleField: BattleField, width: Double, height: Double) : Canvas(width, height) {
    val gameDirector: GameDirector = GameDirector(battleField.nodes)

    init {
        val gc = graphicsContext2D
        gameDirector.render = { lag ->
            for (node in battleField.nodes) {
                gc.drawImage(node.image, node.x + node.vx * lag, node.y + node.vy * lag)
            }
        }
        gameDirector.inputs = {
            //accept user inputs
        }
        style = "-fx-border-color: darkgrey;"

    }

    fun start() {
        gameDirector.start()
    }

    fun stop() {
        gameDirector.stop = true
    }
}