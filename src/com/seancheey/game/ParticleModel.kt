package com.seancheey.game

import javafx.scene.image.Image

/**
 * Created by Seancheey on 16/06/2017.
 * GitHub: https://github.com/Seancheey
 */
open class ParticleModel(override val width: Double, override val height: Double, override val image: Image) : Model {
    override val actionTree: ActionTree = ActionTree()
}