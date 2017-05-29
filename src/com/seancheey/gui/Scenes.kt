package com.seancheey.gui

import javafx.fxml.FXMLLoader
import javafx.scene.Parent

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Scenes {
    val startScene = load("main.fxml")
    val menu = load("menu.fxml")
    val bot_edit = load("bot_edit.fxml")
    //val bot_battle = load("bot_battle.fxml")

    fun load(url: String): Parent {
        return FXMLLoader.load(javaClass.getResource(url))
    }
}