package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.gui.ModelSlot
import com.seancheey.resources.Resources
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class Menu : Initializable {

    @FXML
    var botGroupBox: VBox? = null
    @FXML
    var playerLabel: Label? = null
    @FXML
    var titleImageView: ImageView? = null


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        playerLabel!!.text = "${Config.player.name}'s Robots"
        val group = Config.player.robotGroups[0]
        for (model in group) {
            botGroupBox!!.children.add(ModelSlot(model))
        }
        val imageInputStream = Resources.getResourceInStream("dat/title.png")
        if (imageInputStream != null)
            titleImageView!!.image = Image(imageInputStream)
    }

    fun startGame() {
        Stages.switchScene(Scenes.bot_battle)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun editRobots() {
        Stages.switchScene(Scenes.bot_edit, 1080.0, 670.0)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun settings() {

    }

    fun exit() {
        Stages.primaryStage!!.close()
    }

}