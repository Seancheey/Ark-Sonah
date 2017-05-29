package com.seancheey.gui

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */

import com.seancheey.game.Config
import com.seancheey.game.Player
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
            // id method will be changed later
            Config.player = Player("$username/$password".hashCode().toLong(), username!!.text)
        }
        Stages.stage!!.scene = Scene(Scenes.bot_edit, 1080.0, 670.0)
        Stages.stage!!.hide()
        Stages.stage!!.show()
        Stages.stage!!.isFullScreen = true
    }

    fun register() {

    }

    fun setting() {

    }

    fun credit() {

    }
}
