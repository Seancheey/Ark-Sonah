package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.resources.Resources
import javafx.scene.image.Image

/**
 * Created by Seancheey on 25/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class GuiNode(val gui: javafx.scene.Node, override var field: Battlefield) : Node {
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
}