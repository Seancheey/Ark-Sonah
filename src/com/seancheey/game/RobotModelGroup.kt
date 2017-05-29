package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotModelGroup(robotModels: List<RobotModel>) : Serializable, ArrayList<RobotModel>(Config.botGroupNum) {
    init {
        for (model in robotModels) {
            add(model)
        }
        while (size < Config.botGroupNum) {
            add(RobotModel())
        }
        if (robotModels.size > Config.botGroupNum) {
            removeRange(Config.botGroupNum, robotModels.size)
        }
    }

    override fun add(element: RobotModel): Boolean {
        if (size < Config.botGroupNum)
            return super.add(element)
        else
            return false
    }
}
