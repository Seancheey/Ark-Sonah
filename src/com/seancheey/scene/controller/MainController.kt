package com.seancheey.scene.controller

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

import com.seancheey.game.Config
import com.seancheey.game.PlayerSavesReader
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
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
            indicatorLabel!!.text = "username not found, try register"
        }
    }

    fun register() {
        if (username!!.text == "") {
            indicatorLabel!!.text = "username can't be blank"
            return
        }

        // if (password!!.text == "") {
        //    indicatorLabel!!.text = "password can't be blank"
        //    return
        // }
        val reader = PlayerSavesReader(username!!.text, password!!.text)
        if (reader.hasSaves) {
            indicatorLabel!!.text = "username already exists"
            return
        } else {
            Config.player = reader.newPlayer()
            Config.player.saveData()
            indicatorLabel!!.text = "new player created"
            login()
        }
    }

    fun setting() {

    }

    fun credit() {

    }
}
