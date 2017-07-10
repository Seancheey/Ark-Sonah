package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.game.Game
import com.seancheey.game.battlefield.TestBattlefield
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

    val game = Game(2000, mutableMapOf(Config.player to 1), TestBattlefield())

    init {
        // make sure there is a empty battlefield
        battlePane = BattleInspectPane(game, 0.0, 0.0)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        battleController = this
        // init selection slots
        RobotModelSlot.addAllTo(botGroupBox!!.children, Config.player.robotGroups[0], { battlePane.clickRobot(it) }, true)
        // init battle field
        battlePane = BattleInspectPane(game, Stages.primaryStage!!.width, Stages.primaryStage!!.height - botGroupBox!!.height - 200)
        battleContainer!!.children.add(battlePane)
        botGroupBox!!.toFront()
        // init money label
        moneyLabel!!.text = "money: ${game.gamePlayers[0].money}"
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