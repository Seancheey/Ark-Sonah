package com.seancheey.game

import com.seancheey.resources.Resources
import javafx.scene.image.Image
import java.io.Serializable

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

open class ComponentModel(val name: String, imageURL: String, var health: Int, var weight: Int, var gridWidth: Int, var gridHeight: Int) : Model, Serializable {
    /**
     * pixel width of component
     */
    override val width: Double
        get() = gridWidth * Config.botGridSize
    /**
     * pixel height of component
     */
    override val height: Double
        get() = gridHeight * Config.botGridSize
    /**
     * image of component with size same as width/height specified
     */
    @Suppress("SENSELESS_COMPARISON")
    @Transient final
    override var image: Image
        get() {
            if (field == null) {
                field = getCompImage()
            }
            return field
        }
    /**
     * path of image for initialization of image, also used to recover lost image after serialization
     */
    var imageURL = ""
        set(value) {
            field = value
            if (value != "") {
                image = getCompImage()
            }
        }
    /**
     * function used to modify stats of robot
     */
    var modifyRobot: (robot: RobotNode) -> Unit = {}

    init {
        this.imageURL = imageURL
        image = getCompImage()
    }

    private fun getCompImage(): Image {
        return Image(Resources.getResourceInStream(imageURL), width, height, false, false)
    }

    constructor() : this("Default", "file:dat/test0.png", 10, 10, 10, 10)

}
