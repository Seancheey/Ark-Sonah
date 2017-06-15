package com.seancheey.game.battlefield

import com.seancheey.game.Node
import com.seancheey.game.PlayerInGame
import java.util.*

/**
 * Created by Seancheey on 15/06/2017.
 * GitHub: https://github.com/Seancheey
 */
abstract class DefaultBattlefield(override val players: ArrayList<PlayerInGame>) : Battlefield {
    override val nodes: ArrayList<Node> = arrayListOf()
}