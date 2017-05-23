package com.seancheey.gui

import com.seancheey.game.Model
import com.seancheey.game.Models
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.input.ClipboardContent
import javafx.scene.input.MouseEvent
import javafx.scene.input.TransferMode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.TilePane
import java.net.URL
import java.util.*

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class BotEdit : Initializable {
    @FXML
    var borderPane: BorderPane? = null
    @FXML
    var blocksFlowPane: FlowPane? = null
    @FXML
    var weaponsFlowPane: FlowPane? = null
    @FXML
    var editPane: TilePane? = null

    var holding: Model? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        // add components to flowPane
        for (component in Models.blocks) {
            blocksFlowPane!!.children.add(ModelSlot(component, this))
        }
        for (i in 1..9) {
            editPane!!.children.add(ComponentGrid())
        }
    }
}

class ComponentGrid(var model: Model? = null) : ImageView(Model().image) {
    init {
        id = "component_grid"
        prefHeight(50.0)
        minHeight(50.0)
        minWidth(50.0)
        prefWidth(50.0)
        style = "-fx-background-color: darkgrey"
    }
}

class ModelSlot(val componentModel: Model, val editController: BotEdit) : ImageView(componentModel.imageURL) {

    init {
        id = "model_slot"
        setOnMousePressed { editController.holding = componentModel }
        prefWidth(50.toDouble())
        prefHeight(50.toDouble())
        // bind mouse location to hoverView
        setOnDragDetected { event ->
            drag(event)
        }
    }

    fun drag(event: MouseEvent): Unit {
        val db = startDragAndDrop(TransferMode.COPY, TransferMode.LINK, TransferMode.MOVE)
        val clipboard = ClipboardContent()
        clipboard.putImage(editController.holding?.image)
        db.setContent(clipboard)
        db.dragView = editController.holding?.image
        event.consume()
    }
}