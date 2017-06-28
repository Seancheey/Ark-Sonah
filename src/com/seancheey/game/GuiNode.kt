package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.resources.Resources
import javafx.scene.image.Image

/**
 * Created by Seancheey on 25/06/2017.
 * GitHub: https://github.com/Seancheey
 */
open class GuiNode(val gui: javafx.scene.Node, override var field: Battlefield) : Node {
    override var focusedByPlayer: Boolean = false
    override var requestDeletion: Boolean = false
    override val image: Image = Image(Resources.transparentImageInStream)
    override val width: Double
        get() = gui.boundsInLocal.width
    override val height: Double
        get() = gui.boundsInLocal.height
    override val actionTree: ActionTree = ActionTree()
    override var x: Double = 0.0
    override var y: Double = 0.0
    override var orientation: Double = 0.0
    override val peers: ArrayList<Node> = arrayListOf()
    override val children: ArrayList<Node> = arrayListOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GuiNode) return false

        if (gui != other.gui) return false
        if (field != other.field) return false
        if (image != other.image) return false
        if (actionTree != other.actionTree) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (orientation != other.orientation) return false
        if (peers != other.peers) return false
        if (children != other.children) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gui.hashCode()
        result = 31 * result + field.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + actionTree.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + orientation.hashCode()
        result = 31 * result + peers.hashCode()
        result = 31 * result + children.hashCode()
        return result
    }

}