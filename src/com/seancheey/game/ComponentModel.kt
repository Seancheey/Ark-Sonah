package com.seancheey.game

import javafx.geometry.Dimension2D
import javafx.scene.image.Image
import java.io.Serializable

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

open class ComponentModel(val name: String, imageURL: String, var health: Int, var weight: Int, var gridWidth: Int, var gridHeight: Int) : Model, Serializable {
    override val width: Double
        get() = gridWidth * Config.botGridWidth
    override val height: Double
        get() = gridHeight * Config.botGridWidth
    @Suppress("SENSELESS_COMPARISON")
    @Transient final
    override var image: Image
        get() {
            if (field == null) {
                imageURL = imageURL
            }
            return field
        }
    var imageURL = ""
        set(value) {
            field = value
            if (value != "") {
                image = Image(imageURL, gridWidth * Config.botGridWidth, gridHeight * Config.botGridWidth, false, false)
            }
        }
    var size: Dimension2D
        get() = Dimension2D(gridWidth.toDouble(), gridHeight.toDouble())
        set(value) {
            gridWidth = value.width.toInt()
            gridHeight = value.height.toInt()
        }

    init {
        this.imageURL = imageURL
        image = Image(imageURL, gridWidth * Config.botGridWidth, gridHeight * Config.botGridWidth, false, false)
    }

    constructor() : this("Default", "file:dat/test0.png", 10, 10, 10, 10)

}
