package com.seancheey.gui

import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView

/**
 * Created by Seancheey on 16/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class StatusLabel<T>(val statusName: String, statusValue: T, icon: Image? = null) : Label("$statusName: $statusValue") {
    var statusValue: T = statusValue
        set(value) {
            field = value
            if (statusValue is Double) {
                text = "%-10s: %-4.2f".format(statusName, statusValue)
            } else {
                text = "%-10s: %-4s".format(statusName, statusValue)
            }
        }

    init {
        if (icon != null) {
            graphic = ImageView(icon)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StatusLabel<*>) return false

        if (statusName != other.statusName) return false
        if (statusValue != other.statusValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = statusName.hashCode()
        result = 31 * result + (statusValue?.hashCode() ?: 0)
        return result
    }
}