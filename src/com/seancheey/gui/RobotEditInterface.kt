package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.battlefield.EmptyBattlefield
import com.seancheey.game.model.ComponentModel
import com.seancheey.game.model.ComponentNode
import com.seancheey.game.model.RobotModel

/**
 * Created by Seancheey on 13/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface RobotEditInterface {
    companion object {
        private fun symmetricComponent(component: ComponentNode): ComponentNode? {
            val symX = Config.botGridNum - component.gridX - component.model.gridWidth
            if (symX != component.gridX) {
                return ComponentNode.create(component.model, Config.botGridNum - component.gridX - component.model.gridWidth, component.gridY, EmptyBattlefield())
            } else {
                return null
            }
        }
    }

    var editingRobot: RobotModel
    var editRobotModelStack: ArrayList<RobotModel>
    var symmetricBuild: Boolean

    fun addComponentAt(x: Int, y: Int, model: ComponentModel) {
        addComponent(ComponentNode.create(model, x, y, EmptyBattlefield()))
    }

    fun addComponent(component: ComponentNode) {
        val symComponent = symmetricComponent(component)
        if (symmetricBuild && symComponent != null) {
            editingRobot = RobotModel(editingRobot.name, editingRobot.components + component + symComponent)
        } else {
            editingRobot = RobotModel(editingRobot.name, editingRobot.components + component)
        }
        updateRobotModel()
    }

    fun removeComponent(component: ComponentNode) {
        val symComp = symmetricComponent(component)
        editingRobot = RobotModel(editingRobot.name, editingRobot.components.filterNot { it == component || (symmetricBuild && it == symComp) })
        updateRobotModel()
    }

    fun clearComponents() {
        editingRobot = RobotModel(editingRobot.name, listOf())
        updateRobotModel()
    }

    fun resetRobotModel(model: RobotModel) {
        editingRobot = RobotModel(model.name, model.components)
        updateRobotModel()
        // flush editing stack
        editRobotModelStack.clear()
    }

    fun moveAllComponents(dx: Int, dy: Int) {
        val newComps = editingRobot.components.map { ComponentNode.create(it.model, it.gridX + dx, it.gridY + dy, EmptyBattlefield()) }
        editingRobot = RobotModel(editingRobot.name, newComps)
        updateRobotModel()
    }

    fun undoRobotModel() {
        if (editRobotModelStack.size > 0) {
            editingRobot = editRobotModelStack.last()
            // editingRobot assignment add one change, so remove twice
            editRobotModelStack.remove(editRobotModelStack.last())
            editRobotModelStack.remove(editRobotModelStack.last())
            updateRobotModel()
        }
    }

    fun setRobotModelName(name: String) {
        editingRobot = RobotModel(name, editingRobot.components)
        updateRobotModel()
    }

    fun updateRobotModel()

}