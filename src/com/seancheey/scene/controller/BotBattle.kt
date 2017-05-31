package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.gui.ModelSlot
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.HBox
import java.net.URL
import java.util.*

/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotBattle : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // select player's first BotGroup to initialize
        val models = Config.player.robots[0]
        for (model in models) {
            val robotModelSlot = ModelSlot(model)
            botGroupBox!!.children.add(robotModelSlot)
        }
    }

    @FXML
    var botGroupBox: HBox? = null


    fun menu() {
        Stages.switchScene(Scenes.menu)
    }

}