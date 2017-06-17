package com.seancheey.gui

import com.seancheey.game.RobotModel
import javafx.scene.control.Label

/**
 * Created by Seancheey on 17/06/2017.
 * GitHub: https://github.com/Seancheey
 */
data class WrongMessageLabel(val wrongMessage: RobotModel.WrongMessage) : Label(wrongMessage.message)