package com.seancheey.gui

import com.seancheey.game.Config


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class Menu {
    fun startGame() {
        Stages.switchScene(Scenes.bot_battle)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun editRobots() {
        Stages.switchScene(Scenes.bot_edit, 1080.0, 670.0)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun settings() {

    }
}