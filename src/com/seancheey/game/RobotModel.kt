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
data class RobotModel(var name: String, val components: List<Component<ComponentModel>>) : Serializable {
    @Suppress("SENSELESS_COMPARISON")
    val image: Image

    init {
        image = updateImage()
    }

    constructor() : this("", arrayListOf())


    private fun updateImage(): Image {
        if (components.isEmpty()) {
            println("no bot!")
            return Image("file:dat/norobot.png")
        }
        val writeImage = WritableImage(Config.botPixelSize.toInt(), Config.botPixelSize.toInt())

        val writer = writeImage.pixelWriter
        for (comp in components.map { component -> component }) {
            val compImage = comp.image
            val reader = compImage.pixelReader
            for (readX in 0 until compImage.height.toInt()) {
                for (readY in 0 until compImage.width.toInt()) {
                    val color = reader.getColor(readX, readY)
                    val xPos = comp.x * Config.botGridWidth + readX / compImage.width * Config.botGridWidth * comp.width
                    val yPos = comp.y * Config.botGridWidth + readY / compImage.height * Config.botGridWidth * comp.height
                    writer.setColor(xPos.toInt(), yPos.toInt(), color)
                }
            }
        }
        return writeImage
    }
}
