package com.seancheey.scene.controller

import com.seancheey.scene.Scenes
import com.seancheey.scene.Stages

/**
 * Created by Seancheey on 31/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattleSelect {

    fun menu(): Unit {
        Stages.switchScene(Scenes.menu)
    }
}