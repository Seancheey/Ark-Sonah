package com.seancheey.game

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
abstract class GameDirector(val nodes: ArrayList<Node>) {
    var stop = false
    val MS_PER_UPDATE = Config.updatePerMilisecond

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
                update()
                lag -= MS_PER_UPDATE
            }
            render(lag.toDouble() / MS_PER_UPDATE)
        }
    }

    abstract fun inputs()

    open fun update() {
        for (node in nodes) {
            node.update()
        }
    }

    abstract fun render(lag: Double)
}