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
    val botSize
        get() = botGridSize * 5
    var player: Player = Player(0L, "guest", kotlin.ByteArray(0))
        set(value) {
            if (player.name == "guest") field = value
        }
    var fullScreen = false
    /**
     * save directory relative to Resources class
     */
    var playerSaveDir: String = defaultPlayerSaveDir
    val defaultPlayerSaveDir: String
        get() {
            val osName = System.getProperty("os.name")
            when (osName) {
                "Mac OS X" ->
                    return "${System.getProperty("user.home")}/Library/Application Support/Ark_Sonah/saves/"
                "Windows" ->
                    return "${System.getenv("APPDATA")}\\Ark_Sonah\\saves\\"
                else ->
                    return "saves/"
            }
        }
    /**
     * node update frequency
     */
    val updatePerMilisecond = 20
    /**
     * for threads to close automatically when program stops
     */
    var programClosed = false

    /**
     * each scroll set scale *= (1 + or - scrollSpeedDelta)
     */
    var scrollSpeedDelta = 0.05
        set(value) {
            if (value > 0.5) {
                field = 0.5
            } else {
                field = value
            }
        }
    /**
     * attribute string separator
     */
    val attrSeparator = "|"

    /**
     * player's actual save file path
     */
    fun playerSavePath(name: String = player.name) = "$playerSaveDir$name.arksaves"
}