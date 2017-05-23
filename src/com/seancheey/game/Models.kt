package com.seancheey.game

/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Models {
    val blocks: List<Model> = ComponentReader<Model>("dat/blocks.dat").readAll()
}