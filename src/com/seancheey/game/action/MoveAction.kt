package com.seancheey.game.action

import com.seancheey.game.Node

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class MoveAction(node: Node) : Action(node, {
    node.x += node.vx
    node.y += node.vy
})