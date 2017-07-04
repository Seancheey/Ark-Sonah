package com.seancheey.game

import com.seancheey.game.command.Command
import com.seancheey.game.model.Node

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class GameDirector(val asyncNodes: ArrayList<Node>, var inputs: () -> Unit = {}, var render: (lag: Double) -> Unit = {}) {
    var stop = true
    val started
        get() = !stop
    val MS_PER_UPDATE = Config.updatePerMilisecond
    private val commands: ArrayList<Command> = arrayListOf()
    val nodes: ArrayList<Node>
        get() = syncNodes
    val syncNodes: ArrayList<Node> = arrayListOf()
    var lastTime: Long = System.currentTimeMillis()
    var currentTime: Long = System.currentTimeMillis()
    var elapsed: Int = 0
    var lag = 0

    init {
        syncNodes += asyncNodes
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
            lag = 0
            inputs()
            if (lag < MS_PER_UPDATE) {
                Thread.sleep(MS_PER_UPDATE.toLong() - lag)
                updateTime()
            }
            while (lag > MS_PER_UPDATE) {
                syncNodes()
                executeCommands()
                update()
                lag -= MS_PER_UPDATE
            }
        }
    }

    open fun update() {
        nodes.removeAll(nodes.filter { it.requestDeletion })
        nodes.forEach { it.update() }
    }

    private fun syncNodes() {
        syncNodes.clear()
        syncNodes += asyncNodes
    }

}