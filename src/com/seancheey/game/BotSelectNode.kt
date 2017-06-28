package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.gui.BotSelectCirclePane

/**
 * Created by Seancheey on 28/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class BotSelectNode(playerInGame: PlayerInGame, field: Battlefield) : GuiNode(BotSelectCirclePane(playerInGame.robotGroupUsed), field)
