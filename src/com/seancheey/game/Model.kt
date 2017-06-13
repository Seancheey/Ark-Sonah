package com.seancheey.game

import javafx.scene.image.Image
import java.io.Serializable

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Model : Serializable{
    val image: Image
    val width: Double
    val height: Double
}