package com.seancheey.game

import javafx.geometry.Dimension2D
import javafx.scene.image.Image

/**
* Created by Seancheey on 20/05/2017.
* GitHub: https://github.com/Seancheey
*/

abstract class Component(name: String, imageURL: String, width: Int, height: Int){
    var name = ""
    var image: Image
    var imageURL = ""
    set(value){
        field = value
        if(value != "") {
            println(value)
            image = Image(imageURL)
        }
    }
    private var width = 0
    private var height = 0
    val size: Dimension2D
        get() = Dimension2D(width.toDouble(), height.toDouble())

    init {
        this.name = name
        this.imageURL = imageURL
        this.width = width
        this.height = height
        image = Image(imageURL)
    }

    constructor() : this("Default","cube.png",10,10)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Component) return false

        if (name != other.name) return false
        if (image != other.image) return false
        if (imageURL != other.imageURL) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + imageURL.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }

    override fun toString(): String {
        return "Component(name='$name', imageURL='$imageURL', width=$width, height=$height)"
    }

}
