package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class RobotModelGroup(var robotModels: List<RobotModel>) : Serializable, Iterable<RobotModel> {
    class RobotModelIterator(val robotModels: List<RobotModel>) : Iterator<RobotModel> {
        var cursor = 0
        override fun hasNext(): Boolean {
            return cursor < robotModels.size
        }

        override fun next(): RobotModel {
            if (hasNext()) {
                val current = robotModels[cursor]
                cursor++
                return current
            }
            throw NoSuchElementException()
        }
    }

    init {
        if (robotModels.size < Config.botGroupNum) {
            val botlist = arrayListOf<RobotModel>()
            for (model in robotModels) {
                botlist.add(model)
            }
            while (botlist.size < Config.botGroupNum) {
                botlist.add(RobotModel())
            }
            robotModels = botlist.toList()
        } else if (robotModels.size > Config.botGroupNum) {
            robotModels = robotModels.slice(0 until Config.botGroupNum)
        }
    }

    override fun iterator(): Iterator<RobotModel> {
        return RobotModelIterator(robotModels)
    }
}
