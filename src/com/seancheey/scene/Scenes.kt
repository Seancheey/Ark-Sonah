package com.seancheey.scene

import javafx.fxml.FXMLLoader
import javafx.scene.Parent

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Scenes {
    val main
        get() = load("main.fxml")
    val menu
        get() = load("menu.fxml")
    val edit
        get() = load("edit.fxml")
    val battle
        get() = load("battle.fxml")
    val select
        get() = load("battle_select.fxml")
    val setting
        get() = load("setting.fxml")

    fun load(url: String): Parent {
        return FXMLLoader.load(javaClass.getResource(url))
    }
}