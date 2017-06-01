package com.seancheey.game

import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import java.io.Serializable


/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
/**
 * Designed as an immutable class as robot model
 */
open class RobotModel(var name: String, val components: List<Component<ComponentModel>>) : Model, Serializable {
    override val width: Double
        get() = Config.botWidth
    override val height: Double
        get() = Config.botWidth
    @Suppress("SENSELESS_COMPARISON")
    @Transient final
    override val image: Image
        get() {
            if (field == null) {
                field = updateImage()
            }
            return field
        }

    init {
        image = updateImage()
    }

    constructor() : this("", arrayListOf())


    private fun updateImage(): Image {
        if (components.isEmpty()) {
            return Image("file:dat/norobot.png")
        }
        val writeImage = WritableImage(Config.botPixelSize.toInt(), Config.botPixelSize.toInt())

        val writer = writeImage.pixelWriter
        for (comp in components.map { component -> component }) {
            val compImage = comp.image
            val reader = compImage.pixelReader
            for (readY in 0 until compImage.height.toInt()) {
                for (readX in 0 until compImage.width.toInt()) {
                    val color = reader.getColor(readX, readY)
                    val xPos = comp.x * Config.botGridWidth + readX / compImage.width * comp.width
                    val yPos = comp.y * Config.botGridWidth + readY / compImage.height * comp.height
                    writer.setColor(xPos.toInt(), yPos.toInt(), color)
                }
            }
        }
        return writeImage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RobotModel) return false

        if (name != other.name) return false
        if (components != other.components) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + components.hashCode()
        return result
    }

}
