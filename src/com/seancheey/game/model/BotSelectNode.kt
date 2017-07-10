package com.seancheey.game.model

import com.seancheey.game.PlayerInGame
import com.seancheey.game.battlefield.Battlefield
import com.seancheey.gui.BotSelectCirclePane

/**
 * Created by Seancheey on 28/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotSelectNode(playerInGame: PlayerInGame, field: Battlefield, onClick: (RobotModel) -> Unit = {}) : GuiNode(BotSelectCirclePane(playerInGame.robotGroup, onClick), field)
