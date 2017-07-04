package com.seancheey.game.model

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 16/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class ParticleNode(val model: ParticleModel, override var x: Double, override var y: Double, override var field: Battlefield) : MovableNode, Model by ParticleModel(model.width, model.height, model.image) {
    override var focusedByPlayer: Boolean = false
    override var requestDeletion: Boolean = false
    override val maxSpeed: Double = 100.0
    override val turnSpeed: Double = 0.0
    override val maxAcceleration: Double = 100.0
    override var acceleration: Double = 0.0
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
    override val peers: ArrayList<Node> = field.nodes
    override val children: ArrayList<Node> = arrayListOf()
}