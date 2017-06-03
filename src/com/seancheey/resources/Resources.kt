package com.seancheey.resources

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
object Resources {
    val blocks_dat: String
        get() {
            return getResourceString("dat/blocks.dat")!!
        }

    fun getResourceString(path: String): String? {
        try {
            val buffReader = BufferedReader(InputStreamReader(getResourceInStream(path)))
            val strBuilder = StringBuilder()
            while (true) {
                val line = buffReader.readLine() ?: break
                strBuilder.append("$line\n")
            }
            return strBuilder.toString()
        } catch (e: IOException) {
            return null
        }
    }

    fun getResourceInStream(path: String): InputStream? = javaClass.getResourceAsStream(path)
}