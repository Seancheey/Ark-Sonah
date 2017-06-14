package com.seancheey.gui

import com.seancheey.game.ComponentModel
import com.seancheey.game.DefaultComponent
import com.seancheey.game.RobotModel

/**
 * Created by Seancheey on 13/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface RobotEditInterface {
    var editingRobot: RobotModel

    fun addComponentAt(x: Int, y: Int, model: ComponentModel) {
        addComponent(DefaultComponent.create(model, x, y))
    }

    fun addComponent(component: DefaultComponent) {
        editingRobot = RobotModel(editingRobot.name, editingRobot.components + component)
        updateRobotModel()
    }

    fun removeComponentAt(x: Int, y: Int) {
        editingRobot = RobotModel(editingRobot.name, editingRobot.components.filterNot { it.gridX == x && it.gridY == y })
        updateRobotModel()
    }

    fun clearComponents() {
        editingRobot = RobotModel(editingRobot.name, listOf())
        updateRobotModel()
    }

    fun addAllComponents(model: RobotModel) {
        editingRobot = RobotModel(editingRobot.name, editingRobot.components + model.components)
        updateRobotModel()
    }

    fun resetAllComponents(model: RobotModel) {
        clearComponents()
        addAllComponents(model)
    }

    fun moveAllComponents(dx: Int, dy: Int) {
        val newComps = editingRobot.components.map { DefaultComponent.create(it.model, it.gridX + dx, it.gridY + dy) }
        editingRobot = RobotModel(editingRobot.name, newComps)
        updateRobotModel()
    }

    fun updateRobotModel()

}