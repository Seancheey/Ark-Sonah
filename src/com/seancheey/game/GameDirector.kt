package com.seancheey.game

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

    init {
        syncNodes += asyncNodes
    }

    fun executeCommands() {
        for (command in commands) {
            command.execute()
        }
        commands.clear()
    }

    fun start() {
        var lastTime: Long = System.currentTimeMillis()
        var currentTime: Long
        var elapsed: Int
        var lag: Int = 0

        stop = false
        while (!stop) {
            currentTime = System.currentTimeMillis()
            elapsed = (currentTime - lastTime).toInt()
            lastTime = currentTime
            lag += elapsed

            inputs()
            if (lag < MS_PER_UPDATE) {
                Thread.sleep(MS_PER_UPDATE.toLong() - lag)
                lag += MS_PER_UPDATE - lag
            }
            while (lag > MS_PER_UPDATE) {
                syncNodes()
                executeCommands()
                update()
                lag -= MS_PER_UPDATE
            }
            render(lag.toDouble() / MS_PER_UPDATE)
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