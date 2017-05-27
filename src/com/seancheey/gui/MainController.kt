package com.seancheey.gui

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField


class MainController {
    @FXML
    private var username: TextField? = null
    @FXML
    private var password: TextField? = null

    fun login() {
        val root: Parent = FXMLLoader.load(javaClass.getResource("bot_edit.fxml"))
        PrimaryStage.stage!!.scene = Scene(root, 1080.0, 670.0)
        PrimaryStage.stage!!.hide()
        PrimaryStage.stage!!.show()
        PrimaryStage.stage!!.isFullScreen = true
    }

    fun register() {

    }

    fun setting() {

    }

    fun credit() {

    }
}
