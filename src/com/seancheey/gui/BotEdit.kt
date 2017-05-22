package com.seancheey.gui
import com.seancheey.game.Component
import com.seancheey.game.Components
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.*
import java.net.URL
import java.util.*
import javafx.scene.image.ImageView

/**
* Created by Seancheey on 20/05/2017.
* GitHub: https://github.com/Seancheey
*/

class BotEdit : Initializable{
    @FXML
    var borderPane: BorderPane? = null
    @FXML
    var blocksFlowPane: FlowPane? = null
    @FXML
    var weaponsFlowPane: FlowPane? = null
    private var hoverView: ImageView? = null

    var holding: Component? = null
    set(value){
            holding = value
            hoverView = ImageView(value?.image)
    }

    fun sayHi(): Unit {
        println("Hiiiii")
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        for(component in Components.blocks){
            blocksFlowPane?.children?.add(ComponentSlot(component, this))
        }
    }
}



class ComponentSlot(component: Component, editController: BotEdit) : ImageView(component.imageURL){
    init{
        setOnDragEntered {editController.holding = component}
    }
}
