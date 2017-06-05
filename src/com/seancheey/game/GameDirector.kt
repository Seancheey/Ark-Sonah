package com.seancheey.game

import com.seancheey.game.command.Command

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class GameDirector(val asyncNodes: ArrayList<Node>, var inputs: () -> Unit = {}, var render: (lag: Double) -> Unit = {}) {
    var stop = true
    val started
        get() = !stop
    val MS_PER_UPDATE = Config.updatePerMilisecond
    val commands: ArrayList<Command> = arrayListOf()
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
        while (!stop) {
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
        for (node in nodes) {
            node.update()
        }
    }

    private fun syncNodes() {
        syncNodes.clear()
        syncNodes += asyncNodes
    }

}