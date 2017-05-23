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
import javafx.scene.layout.StackPane
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
        for (i in 1..7*7) {
            editPane!!.children.add(ComponentGrid(this))
        }
    }
}

class ComponentGrid(val editController: BotEdit, model: Model? = null) : StackPane() {
    var imageView: ImageView = ImageView()
    var model: Model? = null
        set(value) {
            field = value
            imageView.image = value?.image
        }

    init {
        width = 52.0
        height = 52.0
        this.model = model
        setOnDragOver { event -> event.acceptTransferModes(TransferMode.MOVE, TransferMode.LINK, TransferMode.COPY);event.consume() }
        setOnDragDropped {event -> imageView.image = editController.holding?.image; event.isDropCompleted = true; event.consume()}
        imageView.fitHeight = 50.0
        imageView.fitWidth = 50.0

        children.add(imageView)

    }

}

class ModelSlot(val componentModel: Model, val editController: BotEdit) : ImageView(componentModel.imageURL) {

    init {
        id = "model_slot"
        setOnMousePressed { editController.holding = componentModel }
        if (image.height > 50 || image.width > 50) {
            fitWidth = 50.0
            fitHeight = 50.0
        }
        // bind mouse location to hoverView
        setOnDragDetected { event ->
            dragBegin(event)
        }
    }

    fun dragBegin(event: MouseEvent): Unit {
        val db = startDragAndDrop(TransferMode.COPY, TransferMode.LINK, TransferMode.MOVE)
        val clipboard = ClipboardContent()
        clipboard.putImage(editController.holding?.image)
        db.setContent(clipboard)
        db.dragView = editController.holding?.image
        event.consume()
    }
}