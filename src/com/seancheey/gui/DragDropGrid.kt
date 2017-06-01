package com.seancheey.gui

import com.seancheey.game.ComponentModel
import com.seancheey.game.Config
import javafx.scene.input.TransferMode
import javafx.scene.layout.StackPane

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */

/**
 * A grid on editPane waiting to be filled by a component
 * It is created when BotEdit pane is initialized
 */
class DragDropGrid(val x: Int, val y: Int, dragComponentEnd: (x: Int, y: Int, model: ComponentModel) -> Unit) : StackPane() {

    var enabled = true

    init {
        minWidth = Config.botGridWidth
        minHeight = Config.botGridWidth
        maxWidth = Config.botGridWidth
        maxHeight = Config.botGridWidth

        setOnDragOver {
            event ->
            if (enabled) {
                event.acceptTransferModes(TransferMode.MOVE, TransferMode.LINK, TransferMode.COPY)
                event.consume()
            }
        }
        setOnDragDropped { event ->
            if (enabled && event.dragboard.hasContent(modelCopyFormat)) {
                dragComponentEnd(x, y, event.dragboard.getContent(modelCopyFormat) as @kotlin.ParameterName(name = "model") ComponentModel)
                event.isDropCompleted = true
                event.consume()
            }
        }
    }
}
