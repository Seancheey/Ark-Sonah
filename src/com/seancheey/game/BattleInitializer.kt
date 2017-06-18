package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.battlefield.TestBattlefield

/**
 * Created by Seancheey on 18/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface BattleInitializer {
    var startPrice: Int
    var playersWithSide: Map<Player, PlayerInGame.Side>
    var battlefieldFactory: () -> Battlefield

    fun create(): Battlefield {
        val field = battlefieldFactory()
        playersWithSide.keys.map {
            val playerInGame = PlayerInGame(it, it.robotGroups[0], playersWithSide[it]!!)
            playerInGame.money = startPrice
            playerInGame
        }.forEach {
            field.players.add(it)
        }
        return field
    }

    fun startBattle()
}

class DefaultBattleInitializer(override var startPrice: Int) : BattleInitializer {
    override var playersWithSide: Map<Player, PlayerInGame.Side> = mapOf(Config.player to PlayerInGame.Side.side1)
    override var battlefieldFactory: () -> Battlefield = { TestBattlefield() }

    override fun startBattle() {
        TODO("not implemented")
    }
}