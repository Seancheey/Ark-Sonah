package com.seancheey.resources

import java.io.*

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
object Resources {
    val components_json: String
        get() = getResourceString("dat/components.json")!!
    val errorImageInStream: InputStream
        get() = getResourceInStream("dat/error.png")!!
    val noRobotImageInStream: InputStream
        get() = getResourceInStream("dat/norobot.png") ?: errorImageInStream
    val arrowImageInStream: InputStream
        get() = getResourceInStream("dat/arrow.png") ?: errorImageInStream
    val titleImageInStream: InputStream
        get() = getResourceInStream("dat/title.png") ?: errorImageInStream

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