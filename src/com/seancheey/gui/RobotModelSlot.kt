package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.RobotModel
import javafx.scene.image.ImageView

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotModelSlot(botModel: RobotModel) : ImageView((botModel.image)) {
    init {
        fitWidth = Config.botWidth
        fitHeight = Config.botWidth
    }
}