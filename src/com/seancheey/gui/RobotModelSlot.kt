package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.RobotModel
import javafx.scene.control.Button
import javafx.scene.image.ImageView

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotModelSlot(botModel: RobotModel) : Button("") {
    init {
        val botView = ImageView(botModel.image)
        botView.fitWidth = Config.botWidth
        botView.fitHeight = Config.botWidth
        graphic = botView

        maxWidth = Config.botWidth
        maxHeight = Config.botWidth
        minWidth = Config.botWidth
        minHeight = Config.botWidth
    }
}