package com.seancheey.gui

import com.seancheey.game.RobotModel
import javafx.collections.ObservableList
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.layout.Pane
import javafx.scene.shape.Circle
import javafx.scene.shape.Shape

/**
 * Created by Seancheey on 21/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotSelectCirclePane(models: ArrayList<RobotModel>, onClick: (RobotModel) -> Unit = {}) : Pane() {
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
        RobotModelSlot.allAllTo(children, models, onClick)
        children.forEachIndexed { i, node ->
            if (node is RobotModelSlot) {
                val centerPoint = centerPoint(i)
                node.layoutX = centerPoint.x - node.width / 2
                node.layoutY = centerPoint.y - node.height / 2
            }
        }
    }

    private fun centerPoint(i: Int): Point2D {
        val nodeSum = children.size
        val fraction = i / nodeSum * 2 * Math.PI
        val midRadius = (outerCircleRadius + innerCircleRadius) / 2
        return Point2D(midRadius * Math.cos(fraction), midRadius * Math.sin(fraction))
    }

    private fun clipPane() {
        val ringShape = Shape.subtract(Circle(outerCircleRadius), Circle(innerCircleRadius))
        clip = ringShape
    }

    override fun getChildren(): ObservableList<Node> {
        return super.getChildren()
    }

}