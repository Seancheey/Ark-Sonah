package com.seancheey.game

/**
 * Created by Seancheey on 24/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Config {
    val botGridNum = 20
    val botGridSize = 20.0
    val botPixelSize
        get() = botGridNum * botGridSize
    val botGroupNum = 8
    val botSiz
        get() = botGridSize * 5
    var player: Player = Player(0L, "guest", kotlin.ByteArray(0))
        set(value) {
            if (player.name == "guest") field = value
        }
    var fullScreen = false
    var playerSaveDir = "saves/"
    val updatePerMilisecond = 20
    fun playerSavePath(name: String) = "$playerSaveDir$name.object"
}