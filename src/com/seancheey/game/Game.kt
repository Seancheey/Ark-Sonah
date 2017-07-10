package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 10/07/2017.
 * GitHub: https://github.com/Seancheey
 */
open class Game(var initialMoney: Int = 2000, playerWithSide: MutableMap<Player, Int> = mutableMapOf(), val field: Battlefield) {
    val gamePlayers: List<PlayerInGame> = playerWithSide.map { PlayerInGame(it.key, it.value, initialMoney) }
    val playerMap: Map<Player, PlayerInGame> = mutableMapOf(*gamePlayers.map { it.player to it }.toTypedArray())

    open fun testWin(): Player? = null
}