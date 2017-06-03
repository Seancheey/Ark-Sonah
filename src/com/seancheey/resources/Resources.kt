package com.seancheey.resources

import java.io.File
import java.io.InputStream

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
object Resources {
    val blocks_dat: File
        get() = getResourceFile("dat/blocks.dat")

    fun playerSaveInputStream(id: String): InputStream? = javaClass.getResourceAsStream("saves/$id.object")

    fun getResourceFile(path: String): File = File(javaClass.getResource(path).file)
}