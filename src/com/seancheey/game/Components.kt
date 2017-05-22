package com.seancheey.game

/**
* Created by Seancheey on 22/05/2017.
* GitHub: https://github.com/Seancheey
*/
object Components {
    val blocks: List<Block> = ComponentReader<Block>("dat/blocks.dat").readAll(Array(2,{Block()}))
}