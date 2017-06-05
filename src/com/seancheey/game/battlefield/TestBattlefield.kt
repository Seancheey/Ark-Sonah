package com.seancheey.game.battlefield

import com.seancheey.game.Config

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class TestBattlefield : Battlefield(arrayListOf(Config.player)) {
    init {
        name = "Classic AI Battle"
    }
}