package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class Command(val from: Player, val to: Node, val execute: () -> Unit) : Serializable

class MoveCommand(from: Player, to: Node) : Command(from, to, {
})