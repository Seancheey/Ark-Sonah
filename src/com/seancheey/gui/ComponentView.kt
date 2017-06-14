package com.seancheey.gui

import com.seancheey.game.*
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
    val component: DefaultComponent
        get() = toComponent()

    init {
        setOnDragDetected { event ->
            dragStart(event, this)
            event.consume()
        }
    }

    fun toComponent(): DefaultComponent {
        if (componentModel is MovementModel)
            return MovementComponent(componentModel, x, y)
        if (componentModel is WeaponModel)
            return WeaponComponent(componentModel, x, y)
        return DefaultComponent(componentModel, x, y)
    }
}
