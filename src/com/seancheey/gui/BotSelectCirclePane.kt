package com.seancheey.gui

import com.seancheey.game.RobotModel
import javafx.geometry.Point2D
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Arc
import javafx.scene.shape.ArcType
import javafx.scene.shape.Circle
import javafx.scene.shape.Shape

/**
 * Created by Seancheey on 21/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotSelectCirclePane(models: ArrayList<RobotModel>, onClick: (RobotModel) -> Unit = {}) : AnchorPane() {
    var outerCircleRadius: Double = 120.0
        set(value) {
            field = value
            clipPane()
        }
    var innerCircleRadius: Double = 50.0
        set(value) {
            field = value
            clipPane()
        }
    val midRadius
        get() = (outerCircleRadius + innerCircleRadius) / 2

    init {
        width = outerCircleRadius * 2
        height = outerCircleRadius * 2
        RobotModelSlot.allAllTo(children, models, onClick)
        children.forEachIndexed { i, node ->
            if (node is RobotModelSlot) {
                val centerPoint = centerPoint(i)
                AnchorPane.setLeftAnchor(node, centerPoint.x - node.maxWidth / 2)
                AnchorPane.setTopAnchor(node, centerPoint.y - node.maxHeight / 2)
                node.clip = nodeClipShape(i, node)
            }
        }
        clipPane()
    }

    private fun nodeAngle(i: Int) = i.toDouble() / children.size * 2 * Math.PI

    private fun nodeClipShape(i: Int, node: RobotModelSlot): Arc {
        val arc = Arc()
        arc.radiusX = outerCircleRadius
        arc.radiusY = outerCircleRadius
        val angleDiff = nodeAngle(1 % children.size) - nodeAngle(0)
        val nodeAngle = nodeAngle(i)
        arc.startAngle = -(nodeAngle - angleDiff / 2) * 180 / Math.PI
        arc.length = -angleDiff * 180 / Math.PI
        arc.centerX = -(Math.cos(nodeAngle) * midRadius - node.maxWidth / 2)
        arc.centerY = -(Math.sin(nodeAngle) * midRadius - node.maxHeight / 2)
        arc.type = ArcType.ROUND
        return arc
    }


    private fun centerPoint(i: Int): Point2D {
        val angle = nodeAngle(i)
        return Point2D(width / 2 + midRadius * Math.cos(angle), height / 2 + midRadius * Math.sin(angle))
    }

    private fun clipPane() {
        val ringShape = Shape.subtract(Circle(width / 2, height / 2, outerCircleRadius), Circle(width / 2, height / 2, innerCircleRadius))
        clip = ringShape
    }
}