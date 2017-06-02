package com.seancheey.resources

import java.io.InputStream
import java.net.URL

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
object Resources {
    val blocks_dat: URL = javaClass.getResource("dat/blocks.dat")

    fun playerSaveInputStream(id: String): InputStream? = javaClass.getResourceAsStream("saves/$id.object")
}