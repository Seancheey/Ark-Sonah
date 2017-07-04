package com.seancheey.game.command

import com.seancheey.game.model.Node
import com.seancheey.game.Player
import java.io.Serializable

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class Command(val from: Player, val to: Node, val execute: () -> Unit) : Serializable

