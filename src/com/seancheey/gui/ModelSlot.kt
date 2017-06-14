package com.seancheey.gui

import com.seancheey.game.Model
import javafx.scene.control.Button
import javafx.scene.image.ImageView

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class ModelSlot(val model: Model, val requestWidth: Double = model.width, val requestHeight: Double = model.height) : Button("") {
    init {
        updateModel(model, requestWidth, requestHeight)
    }

    fun updateModel(model: Model, w: Double = requestWidth, h: Double = requestHeight) {
        //set image size
        val botView = ImageView(model.image)
        botView.fitWidth = w
        botView.fitHeight = h
        graphic = botView
        // set self size
        maxWidth = w
        maxHeight = h
        minWidth = w
        minHeight = h
    }
}