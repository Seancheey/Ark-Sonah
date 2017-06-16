package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 16/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class ParticleNode(val model: ParticleModel, override var x: Double, override var y: Double, override var field: Battlefield) : MovableNode, Model by ParticleModel(model.width, model.height, model.image, model.actionTree) {
    override val maxSpeed: Double = 100.0
    override val turnSpeed: Double = 0.0
    override val maxAcceleration: Double = 100.0
    override var acceleration: Double = 0.0
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
    override val actionTree: ActionTree
        get() = model.actionTree
    override val peers: ArrayList<Node> = field.nodes
    override val children: ArrayList<Node> = arrayListOf()
}