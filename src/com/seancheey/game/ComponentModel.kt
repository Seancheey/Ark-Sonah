package com.seancheey.game

import com.seancheey.resources.Resources
import javafx.scene.image.Image
import java.io.Serializable
import javax.json.Json
import javax.json.JsonObject
import javax.json.JsonObjectBuilder

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
            image = getCompImage()
        }
    /**
     * function used to modify stats of robot
     */
    open var modifyRobot: (robot: RobotNode) -> Unit = {}

    companion object {
        private val varList = listOf("name", "imageURL", "health", "weight", "gridWidth", "gridHeight")
        fun create(j: JsonObject): ComponentModel? {
            if (!varList.any { j.isNull(it) })
                return ComponentModel(j.getString(varList[0]), j.getString(varList[1]), j.getInt(varList[2]), j.getInt(varList[3]), j.getInt(varList[4]), j.getInt(varList[5]))
            return null
        }
    }

    init {
        this.imageURL = imageURL
        image = getCompImage()
    }

    private fun getCompImage(): Image {
        val resInStream = Resources.getResourceInStream(imageURL)
        if (resInStream != null) {
            return Image(resInStream, width, height, false, false)
        } else {
            return Image(Resources.errorImageInStream, width, height, false, false)
        }
    }

    open protected fun createJsonBuilder(): JsonObjectBuilder {
        return Json.createObjectBuilder().add("name", name)
                .add("imageURL", imageURL)
                .add("health", health)
                .add("weight", weight)
                .add("gridWidth", gridWidth)
                .add("gridHeight", gridHeight)
    }
}
