package com.seancheey.gui

import com.seancheey.game.GameConfig
import com.seancheey.game.Model
import com.seancheey.game.Models
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.input.*
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.StackPane
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
    var editPane: AnchorPane? = null
    @FXML
    var nameField: TextField? = null

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
        val width = GameConfig.edit_grid_width.toDouble()
        val num = GameConfig.edit_grid_num
        val size = num * width
        editPane!!.minWidth = size
        editPane!!.maxWidth = size
        // add grid to edit pane
        val grids = arrayListOf<ComponentGrid>()
        for (y in 0..num - 1) {
            for (x in 0..num - 1) {
                val grid = ComponentGrid(this, x, y)
                AnchorPane.setTopAnchor(grid, width * y)
                AnchorPane.setLeftAnchor(grid, width * x)
                grids.add(grid)
            }
        }
        editPane!!.children.addAll(grids)
        nameField!!.setMaxSize(size, size)
    }
}

class ComponentGrid(val editController: BotEdit, val x: Int, val y: Int, model: Model? = null) : StackPane() {
    var imageView: ImageView = ImageView()
    var model: Model? = null
        set(value) {
            field = value
            imageView.image = value?.image
        }

    init {
        this.model = model
        //set size of grid
        val size = GameConfig.edit_grid_width.toDouble()
        minWidth = size
        minHeight = size
        maxWidth = size
        maxHeight = size
        // set size of image
        imageView.fitWidthProperty().bind(widthProperty().add(-4))
        imageView.fitHeightProperty().bind(heightProperty().add(-4))
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
