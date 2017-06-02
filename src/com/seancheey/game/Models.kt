package com.seancheey.game

/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Models {
    val BLOCKS: List<ComponentModel> = ComponentReader<ComponentModel>("src/com/seancheey/resources/dat/BLOCKS.dat").readAll()
}