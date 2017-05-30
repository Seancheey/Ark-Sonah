package com.seancheey.gui

import javafx.scene.Scene

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class Menu {
    fun startGame() {

    }

    fun editRobots() {
        Stages.primaryStage!!.scene = Scene(Scenes.bot_edit, 1080.0, 670.0)
        Stages.primaryStage!!.isFullScreen = true
    }

    fun settings() {

    }
}