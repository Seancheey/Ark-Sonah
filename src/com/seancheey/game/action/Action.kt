package com.seancheey.game.action

import com.seancheey.game.Node

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class Action(val node: Node, val perform: () -> Unit)