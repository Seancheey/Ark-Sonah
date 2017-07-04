package com.seancheey.game.model

import com.seancheey.game.ActionTree
import javafx.scene.image.Image
import java.io.Serializable

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Model : Serializable {
    val image: Image
    val width: Double
    val height: Double
    /**
     * the action tree that a node will perform each frame
     * It will only be used under context of a node
     */
    val actionTree: ActionTree
}