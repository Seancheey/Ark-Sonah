package com.seancheey.game

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class GameDirector(val nodes: ArrayList<Node>, var inputs: () -> Unit = {}, var render: (lag: Double) -> Unit = {}) {
    var stop = false
    val MS_PER_UPDATE = Config.updatePerMilisecond
    val commands: ArrayList<Command> = arrayListOf()

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
            while (lag > MS_PER_UPDATE) {
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

}