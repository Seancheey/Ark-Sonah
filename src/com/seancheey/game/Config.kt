package com.seancheey.game

/**
 * Created by Seancheey on 24/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Config {
    val botGridNum = 20
    val botGridWidth = 20.0
    val botPixelSize
        get() = botGridNum * botGridWidth
    val botGroupNum = 8
    val botWidth
        get() = botGridWidth * 5
    var player: Player = Player(0L, "guest")
        set(value) {
            if (player.name == "guest") field = value
        }
    var fullScreen = false
}