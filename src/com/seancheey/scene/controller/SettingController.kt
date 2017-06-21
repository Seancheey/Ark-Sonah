package com.seancheey.scene.controller

import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.Initializable
import java.net.URL
import java.util.*

/**
 * Created by Seancheey on 20/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class SettingController : Initializable {
    override fun initialize(location: URL?, resources: ResourceBundle?) {}

    fun menuButtonPressed() {
        Stages.switchScene(Scenes.menu)
    }
}