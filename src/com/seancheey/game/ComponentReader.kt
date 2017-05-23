package com.seancheey.game

import java.io.File

@Suppress("UNCHECKED_CAST")
/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class ComponentReader<out T>(path: String) {
    private var path = ""
    private var content: List<String>

    init {
        this.path = path
        content = File(path).readLines()
    }

    private fun toComponent(type: String, line: String): T {
        val args = line.split(" ")
        args[0].replace('_', ' ')
        when (type) {
            "Block", "block", "Model", "model" ->
                return ModelFactory.createModel(args) as T
            else ->
                throw Exception("Unknow exception called: $type")
        }
    }

    fun readAll(): List<T> {
        val array: ArrayList<T> = arrayListOf()
        val type = content[0]
        for ((i, line) in content.slice(1..content.size - 1).withIndex()) {
            array.add(toComponent(type, line))
        }
        return array.toList()
    }

}