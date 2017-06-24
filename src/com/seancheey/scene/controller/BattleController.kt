package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.game.DefaultBattleInitializer
import com.seancheey.game.battlefield.EmptyBattlefield
import com.seancheey.gui.BattleInspectPane
import com.seancheey.gui.RobotModelSlot
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
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
    var battleContainer: StackPane? = null
    @FXML
    var moneyLabel: Label? = null

    var battlePane: BattleInspectPane

    init {
        // make sure there is a empty battlefield
        battlePane = BattleInspectPane(EmptyBattlefield(), 0.0, 0.0)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        battleController = this
        // select player's first BotGroup to initialize
        val models = Config.player.robotGroups[0]
        // init selection slots
        RobotModelSlot.allAllTo(botGroupBox!!.children, models, { battlePane.clickRobot(it) })
        // init battle field
        val battleField = DefaultBattleInitializer(2000).create()
        battlePane = BattleInspectPane(battleField, Stages.primaryStage!!.width, Stages.primaryStage!!.height - botGroupBox!!.height - 200)
        battleContainer!!.children.add(battlePane)
        botGroupBox!!.toFront()
        // init money label
        moneyLabel!!.text = "money: ${battleField.players[0].money}"
    }

    fun menu() {
        Stages.switchScene(Scenes.menu)
        battlePane.battleCanvas.stop()
    }

    fun start() {
        battlePane.battleCanvas.start()
    }

    fun pause() {
        battlePane.battleCanvas.stop()
    }

}