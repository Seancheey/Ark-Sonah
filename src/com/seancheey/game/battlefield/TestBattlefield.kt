package com.seancheey.game.battlefield

import com.seancheey.game.PlayerInGame
import java.util.*

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class TestBattlefield(players: ArrayList<PlayerInGame>) : DefaultBattlefield(players) {
    override var name: String = "Test Battlefield"
    override val width: Double = 2000.0
    override val height: Double = 1200.0
}