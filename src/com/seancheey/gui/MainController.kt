package com.seancheey.gui

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

import com.seancheey.game.Config
import com.seancheey.game.PlayerSavesReader
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField


class MainController {
    @FXML
    private var username: TextField? = null
    @FXML
    private var password: TextField? = null
    @FXML
    private var indicatorLabel: Label? = null

    fun login() {
        if (username!!.text == "") {
            username!!.text = "guest"
        }

        val reader = PlayerSavesReader(username!!.text, password!!.text)
        if (reader.hasSaves) {
            if (reader.passwordCorrect()) {
                Config.player = reader.readPlayer()!!
                Stages.switchScene(Scenes.menu, 800.0, 600.0)
            } else {
                indicatorLabel!!.text = "password not correct"
            }
        } else {
            Config.player = reader.newPlayer()
            indicatorLabel!!.text = "new player created"
            Stages.switchScene(Scenes.menu, 800.0, 600.0)
        }
    }

    fun register() {

    }

    fun setting() {

    }

    fun credit() {

    }
}
