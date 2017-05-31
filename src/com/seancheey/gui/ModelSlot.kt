package com.seancheey.gui

import com.seancheey.game.Model
import javafx.scene.control.Button
import javafx.scene.image.ImageView

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class ModelSlot(model: Model) : Button("") {
    init {
        //set image size
        val botView = ImageView(model.image)
        botView.fitWidth = model.width
        botView.fitHeight = model.height
        graphic = botView
        // set self size
        maxWidth = model.width
        maxHeight = model.height
        minWidth = model.width
        minHeight = model.height
    }
}