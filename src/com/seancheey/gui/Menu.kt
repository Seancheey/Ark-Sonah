package com.seancheey.gui

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class Menu {
    fun startGame() {
        Stages.switchScene(Scenes.bot_battle)
        Stages.primaryStage!!.isFullScreen = true
    }

    fun editRobots() {
        Stages.switchScene(Scenes.bot_edit, 1080.0, 670.0)
        Stages.primaryStage!!.isFullScreen = true
    }

    fun settings() {

    }
}