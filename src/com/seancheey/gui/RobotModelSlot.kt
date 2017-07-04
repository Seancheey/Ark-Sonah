package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.model.Model
import com.seancheey.game.model.RobotModel
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.input.MouseEvent

/**
 * Created by Seancheey on 14/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotModelSlot(val robotModel: RobotModel, onClick: (RobotModel) -> Unit = {}, onDragStart: (MouseEvent, RobotModel) -> Unit = { _, _ -> }, var verifyValid: Boolean = false) : ModelSlot(robotModel, Config.botDisplaySize, Config.botDisplaySize, robotModel.idleImage) {
    var onClick: (RobotModel) -> Unit = {}
        set(value) {
            setOnMouseClicked {
                if (!verifyValid || robotModel.verify().isEmpty())
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
        fun allAllTo(children: ObservableList<Node>, models: List<RobotModel>, onClick: (RobotModel) -> Unit = {}, verifyValid: Boolean = false) {
            models.forEach { children.add(RobotModelSlot(it, onClick, verifyValid = verifyValid)) }
        }
    }

    override fun updateModel(model: Model, w: Double, h: Double, imageUsed: Image) {
        if (model is RobotModel) {
            super.updateModel(model, w, h, model.idleImage)
        } else {
            super.updateModel(model, w, h, imageUsed)
        }
    }

    init {
        this.onClick = onClick
        this.onDragStart = onDragStart
    }
}