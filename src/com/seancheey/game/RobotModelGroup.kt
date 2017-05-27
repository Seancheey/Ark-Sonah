package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class RobotModelGroup(var robotModels: List<RobotModel>) : Serializable {
    init {
        if (robotModels.size > Config.botGroupNum) {
            robotModels = robotModels.slice(0 until Config.botGroupNum)
        }
    }
}