package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.RobotModel
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.input.MouseEvent

/**
 * Created by Seancheey on 14/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotModelSlot(val robotModel: RobotModel, onClick: (RobotModel) -> Unit = {}, onDragStart: (MouseEvent, RobotModel) -> Unit = { _, _ -> }) : ModelSlot(robotModel, Config.botDisplaySize, Config.botDisplaySize) {
    var onClick: (RobotModel) -> Unit = {}
        set(value) {
            setOnMouseClicked {
                value(robotModel)
            }
            field = value
        }
    var onDragStart: (MouseEvent, RobotModel) -> Unit = { _, _ -> }
        set(value) {
            setOnDragDetected { mouseEvent ->
                value(mouseEvent, robotModel)
            }
            field = value
        }

    companion object {
        fun allAllTo(children: ObservableList<Node>, models: List<RobotModel>, onClick: (RobotModel) -> Unit = {}) {
            models.forEach { children.add(RobotModelSlot(it, onClick)) }
        }
    }

    init {
        this.onClick = onClick
        this.onDragStart = onDragStart
    }
}