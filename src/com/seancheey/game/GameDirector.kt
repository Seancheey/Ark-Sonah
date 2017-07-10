package com.seancheey.game

import com.seancheey.game.command.Command
import com.seancheey.game.model.Node

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class GameDirector(val game: Game, var render: (lag: Double) -> Unit = {}) {
    /**
     * public access cannot modify nodes, which may lead to concurrence issue
     */
    val nodes: List<Node> = game.field.nodes
    /**
     * only for private access to modify nodes
     */
    private val mutableNodes: ArrayList<Node> = game.field.nodes
    /**
     * stop indicates the game director is not running
     */
    var stop = true
    /**
     * started indicates the game director is running
     */
    val started
        get() = !stop
    /**
     * update time frame
     */
    val MS_PER_UPDATE = Config.updatePerMilisecond
    /**
     * player's command waiting to be executed
     */
    private val commands: ArrayList<Command> = arrayListOf()

    var lastTime: Long = System.currentTimeMillis()
    var currentTime: Long = System.currentTimeMillis()
    var elapsed: Int = 0
    var lag = 0

    init {
        updateTime()
    }

    fun command(command: Command) {
        commands.add(command)
    }

    fun commandTo(node: Node, player: Player, execute: () -> Unit) {
        commands.add(Command(player, node, execute))
    }


    fun executeCommands() {
        for (command in commands) {
            command.execute()
        }
        commands.clear()
    }

    fun updateTime() {
        currentTime = System.currentTimeMillis()
        elapsed = (currentTime - lastTime).toInt()
        lastTime = currentTime
        lag += elapsed
    }

    fun start() {
        stop = false
        while (!stop && !Config.programClosed) {
            updateTime()
            if (lag < MS_PER_UPDATE) {
                Thread.sleep(MS_PER_UPDATE.toLong() - lag)
                updateTime()
            }
            while (lag > MS_PER_UPDATE) {
                executeCommands()
                update()
                updateTime()
                lag -= MS_PER_UPDATE
            }
        }
    }

    fun update() {
        mutableNodes.addAll(game.field.nodeAddQueue)
        game.field.nodeAddQueue.clear()
        mutableNodes.removeAll(nodes.filter { it.requestDeletion })
        nodes.forEach { it.update() }
    }
}