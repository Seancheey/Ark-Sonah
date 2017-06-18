package com.seancheey.game.battlefield

import com.seancheey.game.Node
import com.seancheey.game.PlayerInGame

/**
 * Created by Seancheey on 15/06/2017.
 * GitHub: https://github.com/Seancheey
 */
abstract class DefaultBattlefield: Battlefield {
    override val nodes: ArrayList<Node> = arrayListOf()
    override val players: ArrayList<PlayerInGame> = arrayListOf()
}