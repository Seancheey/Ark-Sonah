package com.seancheey.resources

import java.io.*

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
object Resources {
    val blocks_dat: String
        get() {
            return getResourceString("dat/blocks.dat")!!
        }
    val components_json: String
        get() = getResourceString("dat/components.json")!!

    fun getResourceString(path: String): String? {
        try {
            val buffReader = BufferedReader(InputStreamReader(getResourceInStream(path)) as Reader)
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