package com.seancheey.gui

import com.seancheey.game.GameConfig
import com.seancheey.game.Model
import com.seancheey.game.Models
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.input.*
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
        initModelFlowPanes()
        initEditPane()
    }

    private fun initModelFlowPanes(): Unit {
        for (component in Models.blocks) {
            blocksFlowPane!!.children.add(ModelSlot(component, this))
        }
    }

    private fun initEditPane(): Unit {
        val size = GameConfig.edit_grid_num * GameConfig.edit_grid_width + GameConfig.edit_grid_width - 1
        editPane!!.minWidth = size.toDouble()
        editPane!!.maxWidth = size.toDouble()
        // add grid to edit pane
        for (i in 1..GameConfig.edit_grid_num * GameConfig.edit_grid_num) {
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
        minWidth = GameConfig.edit_grid_width.toDouble()
        minHeight = GameConfig.edit_grid_width.toDouble()
        this.model = model
        imageView.fitWidthProperty().bind(widthProperty().add(-6))
        imageView.fitHeightProperty().bind(heightProperty().add(-6))
        children.add(imageView)
        setOnDragOver { event -> event.acceptTransferModes(TransferMode.MOVE, TransferMode.LINK, TransferMode.COPY);event.consume() }
        setOnDragDropped { event -> dragDropped(event) }
    }

    fun dragDropped(event: DragEvent): Unit {
        if (event.dragboard.hasContent(modelFormat)) {
            model = event.dragboard.getContent(modelFormat) as Model
            event.isDropCompleted = true
            editController.holding = null
            event.consume()
        }
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
        clipboard.put(modelFormat, editController.holding)
        db.setContent(clipboard)

        db.dragView = editController.holding?.image
        event.consume()
    }
}

object modelFormat : DataFormat("object/model")
