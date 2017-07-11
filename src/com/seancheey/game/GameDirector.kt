package com.seancheey.game

import com.seancheey.game.command.Command
import com.seancheey.game.model.Node
import javafx.animation.AnimationTimer

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class GameDirector(val game: Game, var render: (nodes: List<Node>, lag: Double) -> Unit = { _, _ -> }) {
    /**
     * public access cannot modify mutableNodes, which may lead to concurrence issue
     */
    val nodes: List<Node> = game.field.nodes
    /**
     * only for private access to modify mutableNodes
     */
    private val mutableNodes: ArrayList<Node> = game.field.mutableNodes
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
        if (!started) {
            stop = false
            val timer = object : AnimationTimer() {
                override fun handle(now: Long) {
                    if (!stop && !Config.programClosed)
                        gameLoop()
                    else
                        stop()
                }
            }
            timer.start()
        }
    }

    fun update() {
        // add nodes
        mutableNodes.addAll(game.field.nodeAddQueue)
        game.field.nodeAddQueue.clear()
        // remove nodes
        mutableNodes.removeAll(nodes.filter { it.requestDeletion })
        // update rest nodes
        nodes.forEach { it.update() }
    }

    private fun gameLoop() {
        updateTime()
        if (lag < MS_PER_UPDATE) {
            Thread.sleep(MS_PER_UPDATE.toLong() - lag)
            updateTime()
        }
        while (lag > MS_PER_UPDATE) {
            executeCommands()
            update()
            render(nodes, lag.toDouble())
            updateTime()
            lag -= MS_PER_UPDATE
        }
    }
}