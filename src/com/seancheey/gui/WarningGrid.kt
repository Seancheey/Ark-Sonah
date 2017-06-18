package com.seancheey.gui

import com.seancheey.game.Config
import javafx.scene.layout.Pane

/**
 * Created by Seancheey on 18/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class WarningGrid() : Pane() {
    init {
        minWidth = Config.botGridSize
        minHeight = Config.botGridSize
        maxWidth = Config.botGridSize
        maxHeight = Config.botGridSize
        isMouseTransparent = true
    }
}