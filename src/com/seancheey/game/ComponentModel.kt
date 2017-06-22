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

open class ComponentModel(val name: String, imageURL: String, var health: Int, var weight: Int, var gridWidth: Int, var gridHeight: Int, var price: Int) : Model, Serializable {
    override final val actionTree: ActionTree = ActionTree()
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

    /**
     * special attributes of a component
     */
    val attributes: ArrayList<Attribute> = arrayListOf()


    companion object {
        val requiredKeys = listOf("name", "imageURL", "health", "weight", "gridWidth", "gridHeight", "price")
        protected val specialKey = "special"
        fun create(j: JsonObject): ComponentModel? {
            if (requiredKeys.any { it !in j.keys })
                return null
            val model = ComponentModel(j.getString(requiredKeys[0]), j.getString(requiredKeys[1]), j.getInt(requiredKeys[2]), j.getInt(requiredKeys[3]), j.getInt(requiredKeys[4]), j.getInt(requiredKeys[5]), j.getInt(requiredKeys[6]))
            if (specialKey in j.keys) {
                // deal with special attributes
                val attrString: String = j.getJsonString(specialKey).string
                attrString.split(Config.attrSeparator)
                        .mapNotNull { Attribute.getAttribute(it) }
                        .forEach { model.attributes.add(it) }
            } else {
            }
            return model
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
                .add("special", attributes.joinToString(separator = Config.attrSeparator))
    }
}
