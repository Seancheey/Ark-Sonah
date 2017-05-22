package com.seancheey.game

import java.io.File

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class ComponentReader<T : Component>(path: String) {
    private var path = ""
    private var content: List<String>

    init {
        this.path = path
        content = File(path).readLines()
    }

    private fun toComponent(type: String, line: String): T {
        val args = line.split(" ")
        println("args are: $args")
        args[0].replace('_', ' ')
        when (type) {
            "Block", "block" -> {
                if (args[2].toIntOrNull() != null && args[3].toIntOrNull() != null && args[4].toIntOrNull() != null) {
                    return Block(args[0], args[1], args[2].toInt(), args[3].toInt(), args[4].toInt()) as T
                }
                else{
                    throw Exception("arguments in the line cannot be appropriately casted: $args")
                }
            }
            else -> {
                throw Exception("Unknow exception called: $type")
            }
        }
    }

    fun readAll(array: Array<T>): List<T> {
        val type = content[0]
        for ((i, line) in content.slice(1..content.size-1).withIndex()) {
            println("i $i with line:\n $line")
            array[i] = toComponent(type, line)
        }
        return array.toList()
    }

}