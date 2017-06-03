package com.seancheey.game

import com.seancheey.resources.Resources


/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Models {
    val BLOCKS: List<ComponentModel> = ComponentReader<ComponentModel>(Resources.blocks_dat).readAll()
}