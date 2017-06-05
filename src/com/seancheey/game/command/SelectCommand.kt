package com.seancheey.game.command

import com.seancheey.game.Node
import com.seancheey.game.Player

/**
 * Created by Seancheey on 04/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class SelectCommand(from: Player, to: Node, execute: () -> Unit) : Command(from, to, execute)
