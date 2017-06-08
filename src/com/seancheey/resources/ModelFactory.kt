package com.seancheey.resources

import com.seancheey.game.ComponentModel

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */

object ModelFactory {
    private val modelParamNum = 6

    fun createModel(args: List<String>): ComponentModel {
        if (args.size == modelParamNum) {
            return ComponentModel(args[0], args[1], args[2].toInt(), args[3].toInt(), args[4].toInt(), args[5].toInt())
        } else {
            throw Exception("Argument number required is different from received\nRequired: ${modelParamNum}, Received: ${args.size}")
        }
    }
}