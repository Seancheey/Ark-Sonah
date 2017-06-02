package com.seancheey.game

import java.io.File

@Suppress("UNCHECKED_CAST")
/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class ComponentReader<out T>(private var path: String) {
    private var content: List<String>

    init {
        this.path = path
        content = File(path).readLines()
    }

    private fun toComponent(type: String, line: String): T? {
        val args = line.split(" ")
        args[0].replace('_', ' ')
        if (args.size < 5)
            return null
        when (type) {
            "Block", "block", "ComponentModel", "componentModel" ->
                return ModelFactory.createModel(args) as? T
            else ->
                throw Exception("Unknow exception called: $type")
        }
    }

    fun readAll(): List<T> {
        val array: ArrayList<T> = arrayListOf()
        val type = content[0]
        // go through all lines except first one
        for (line in content.slice(1..content.size - 1)) {
            val c = toComponent(type, line)
            if (c != null)
                array.add(c)
        }
        return array.toList()
    }

}