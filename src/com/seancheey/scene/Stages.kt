package com.seancheey.scene

import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage


/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

object Stages {
    var primaryStage: Stage? = null

    fun switchScene(root: Parent, width: Double = 800.0, height: Double = 600.0) {
        primaryStage!!.scene = Scene(root, width, height)
        // put stage at center
        val primScreenBounds = Screen.getPrimary().visualBounds
        primaryStage!!.x = (primScreenBounds.width - primaryStage!!.width) / 2
        primaryStage!!.y = (primScreenBounds.height - primaryStage!!.height) / 2
    }
}