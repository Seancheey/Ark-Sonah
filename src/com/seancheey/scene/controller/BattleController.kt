package com.seancheey.scene.controller

import com.seancheey.game.BattleField
import com.seancheey.game.Config
import com.seancheey.gui.BattlePane
import com.seancheey.gui.ModelSlot
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.concurrent.Task
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.HBox
import java.net.URL
import java.util.*

/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattleController : Initializable {
    companion object {
        var battleController: BattleController? = null
    }

    var battleController: BattleController?
        get() = Companion.battleController
        set(value) {
            Companion.battleController = value
        }

    @FXML
    var botGroupBox: HBox? = null
    @FXML
    var battleContainer: AnchorPane? = null

    var battlePane: BattlePane = BattlePane(BattleField(arrayListOf()), 500.0, 500.0)

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        battleController = this
        // select player's first BotGroup to initialize
        val models = Config.player.robots[0]
        // init selection slots
        for (model in models) {
            val robotModelSlot = ModelSlot(model)
            botGroupBox!!.children.add(robotModelSlot)
        }
        // init battle field
        battleContainer!!.children.add(battlePane)
    }

    fun menu() {
        Stages.switchScene(Scenes.menu)
    }

    fun start() {
        Thread(object : Task<Unit>() {
            override fun call() {
                battlePane.start()
            }
        }).start()
    }

    fun pause() {
        battlePane.stop()
    }

}