package com.seancheey.gui

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

import com.seancheey.game.Config
import com.seancheey.game.Player
import com.seancheey.game.PlayerSavesReader
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.TextField


class MainController {
    @FXML
    private var username: TextField? = null
    @FXML
    private var password: TextField? = null

    fun login() {
        if (username!!.text != "") {
            username!!.text = "guest"
        }
        // id method will be changed later
        val id = username!!.text.toHashSet().hashCode().toLong()
        Config.player = PlayerSavesReader("saves/$id.object").readPlayer() ?: Player(id, username!!.text)

        Stages.primaryStage!!.scene = Scene(Scenes.menu, 800.0, 600.0)
    }

    fun register() {

    }

    fun setting() {

    }

    fun credit() {

    }
}
