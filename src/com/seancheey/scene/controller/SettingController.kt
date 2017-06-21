package com.seancheey.scene.controller

import com.seancheey.resources.Resources
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.net.URL
import java.util.*

/**
 * Created by Seancheey on 20/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class SettingController : Initializable {
    @FXML
    var menuButton: Button? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val arrowImage = ImageView(Image(Resources.arrowImageInStream, 30.0, 30.0, false, false))
        arrowImage.rotate = 270.0
        menuButton!!.graphic = arrowImage
    }

    fun menuButtonPressed() {
        Stages.switchScene(Scenes.menu)
    }
}