package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.gui.RobotModelSlot
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
class MenuController : Initializable {

    @FXML
    var botGroupBox: VBox? = null
    @FXML
    var playerLabel: Label? = null
    @FXML
    var titleImageView: ImageView? = null


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // set player label
        playerLabel!!.text = "${Config.player.name}'s Robots"

        // add player's first robot group
        RobotModelSlot.allAllTo(botGroupBox!!.children, Config.player.robotGroups[0])

        // put title image
        titleImageView!!.image = Image(Resources.titleImageInStream)
    }

    fun startGameButtonPressed() {
        Stages.switchScene(Scenes.bot_battle)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun editRobotsButtonPressed() {
        Stages.switchScene(Scenes.bot_edit, 1080.0, 670.0)
        if (Config.fullScreen)
            Stages.primaryStage!!.isFullScreen = true
    }

    fun settingsButtonPressed() {

    }

    fun exitButtonPressed() {
        Stages.primaryStage!!.close()
        Config.programClosed = true
    }

}