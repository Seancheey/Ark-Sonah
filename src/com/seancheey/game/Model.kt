package com.seancheey.game

import javafx.geometry.Dimension2D
import javafx.scene.image.Image
import java.io.Serializable

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

open class Model(val name: String, imageURL: String, var health: Int, var weight: Int, var width: Int, var height: Int) : Serializable {
    @Transient
    var image: Image
        get() {
            if (field == null) {
                field = Image(imageURL)
            }
            return field
        }
    var imageURL = ""
        set(value) {
            field = value
            if (value != "") {
                image = Image(imageURL)
            }
        }
    var size: Dimension2D
        get() = Dimension2D(width.toDouble(), height.toDouble())
        set(value) {
            width = value.width.toInt()
            height = value.height.toInt()
        }

    init {
        this.imageURL = imageURL
        image = Image(imageURL)
    }

    constructor() : this("Default", "file:dat/cube.png", 10, 10, 10, 10)

    override fun toString(): String {
        return "Model(" +
                "name='$name'," +
                "health=$health," +
                "weight=$weight," +
                "width=$width," +
                "height=$height," +
                "imageURL='$imageURL'" +
                ")"
    }

}
