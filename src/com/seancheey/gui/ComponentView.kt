package com.seancheey.gui

import com.seancheey.game.battlefield.EmptyBattlefield
import com.seancheey.game.model.ComponentModel
import com.seancheey.game.model.ComponentNode
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */

/**
 * A component of a robot
 * It is created when a player drags component componentModel from a ComponentModelSlot to any grid
 */
class ComponentView(val componentModel: ComponentModel, val x: Int, val y: Int, val dragStart: (event: MouseEvent, compView: ComponentView) -> Unit) : ImageView(componentModel.image) {
    val component: ComponentNode
        get() = ComponentNode.create(componentModel, x, y, EmptyBattlefield())

    init {
        setOnDragDetected { event ->
            dragStart(event, this)
            event.consume()
        }
    }
}
