package com.seancheey.gui

import com.seancheey.game.RobotModel
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import javafx.scene.shape.Shape

/**
 * Created by Seancheey on 21/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotSelectCirclePane(val models: ArrayList<RobotModel>) : Pane() {
    var outerCircleRadius: Double = 300.0
        set(value) {
            field = value
            clipPane()
        }
    var innerCircleRadius: Double = 200.0
        set(value) {
            field = value
            clipPane()
        }

    init {
        width = outerCircleRadius * 2
        height = outerCircleRadius * 2
        
    }

    private fun clipPane() {
        val ringShape = Shape.subtract(Circle(outerCircleRadius), Circle(innerCircleRadius))
        clip = ringShape
    }

    override fun getChildren(): ObservableList<Node> {
        return super.getChildren()
    }

}