package com.seancheey.gui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class MainStage : Application() {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("main.fxml"))
        primaryStage.title = "Ark Sonah"
        primaryStage.scene = Scene(root, 800.0, 600.0)
        primaryStage.show()
        Stages.primaryStage = primaryStage
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainStage::class.java)
        }
    }

}