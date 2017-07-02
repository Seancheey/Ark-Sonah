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

open class ComponentModel(val name: String, imageURL: String, var health: Int, var weight: Int, var gridWidth: Int, var gridHeight: Int, var price: Int, var visualWidth: Int = gridWidth, var visualHeight: Int = gridHeight) : Model, Serializable {
    override final val actionTree: ActionTree = ActionTree()
    /**
     * pixel width of component
     */
    override val width: Double
        get() = visualWidth * Config.botGridSize
    /**
     * pixel height of component
     */
    override val height: Double
        get() = visualHeight * Config.botGridSize
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

    val xCorrect: Int
        get() = ((visualWidth - gridWidth) / 2 * Config.botGridSize).toInt()
    val yCorrect: Int
        get() = ((visualHeight - gridHeight) / 2 * Config.botGridSize).toInt()

    companion object {
        protected object keys {
            val name = "name"
            val imageURL = "imageURL"
            val health = "health"
            val weight = "weight"
            val gridWidth = "gridWidth"
            val gridHeight = "gridHeight"
            val price = "price"
            val special = "special"
            val visualWidth = "visualWidth"
            val visualHeight = "visualHeight"
        }

        val requiredKeys = listOf(keys.name, keys.imageURL, keys.health, keys.weight, keys.gridWidth, keys.gridHeight, keys.price)

        fun create(j: JsonObject): ComponentModel? {
            if (requiredKeys.any { it !in j.keys })
                return null
            val model = ComponentModel(j.getString(requiredKeys[0]), j.getString(requiredKeys[1]), j.getInt(requiredKeys[2]), j.getInt(requiredKeys[3]), j.getInt(requiredKeys[4]), j.getInt(requiredKeys[5]), j.getInt(requiredKeys[6]))

            if (keys.special in j.keys) {
                val attrString: String = j.getJsonString(keys.special).string
                attrString.split(Config.attrSeparator)
                        .mapNotNull { Attribute.getAttribute(it) }
                        .forEach { model.attributes.add(it) }
            }
            if (keys.visualWidth in j.keys) {
                model.visualWidth = j.getInt(keys.visualWidth)
            }
            if (keys.visualHeight in j.keys) {
                model.visualHeight = j.getInt(keys.visualHeight)
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
        return Json.createObjectBuilder().add(keys.name, name)
                .add(keys.imageURL, imageURL)
                .add(keys.health, health)
                .add(keys.weight, weight)
                .add(keys.gridWidth, gridWidth)
                .add(keys.gridHeight, gridHeight)
                .add(keys.special, attributes.joinToString(separator = Config.attrSeparator))
                .add(keys.visualWidth, visualWidth)
                .add(keys.visualHeight, visualHeight)
    }

    override fun toString(): String {
        return "ComponentModel(name='$name', health=$health, weight=$weight, gridWidth=$gridWidth, gridHeight=$gridHeight, price=$price, visualWidth=$visualWidth, visualHeight=$visualHeight, imageURL='$imageURL')"
    }

}
