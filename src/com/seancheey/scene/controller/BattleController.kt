package com.seancheey.scene.controller

import com.seancheey.game.Config
import com.seancheey.game.PlayerInGame
import com.seancheey.game.battlefield.EmptyBattlefield
import com.seancheey.game.battlefield.TestBattlefield
import com.seancheey.gui.BattlePane
import com.seancheey.gui.RobotModelSlot
import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.AnchorPane
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

    var battlePane: BattlePane

    init {
        // make sure there is a empty battlefield
        battlePane = BattlePane(EmptyBattlefield(), 0.0, 0.0)
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        battleController = this
        // select player's first BotGroup to initialize
        val models = Config.player.robotGroups[0]
        // init selection slots
        RobotModelSlot.allAllTo(botGroupBox!!.children, models, { battlePane.clickRobot(it) })
        // init battle field
        val testField = TestBattlefield(arrayListOf(PlayerInGame(Config.player, Config.player.robotGroups[0], PlayerInGame.Side.side1)))
        battlePane = BattlePane(testField, Stages.primaryStage!!.width, Stages.primaryStage!!.height - botGroupBox!!.height - 200)
        battleContainer!!.children.add(battlePane)
        botGroupBox!!.toFront()
    }

    fun menu() {
        Stages.switchScene(Scenes.menu)
        battlePane.stop()
    }

    fun start() {
        battlePane.start()
    }

    fun pause() {
        battlePane.stop()
    }

}