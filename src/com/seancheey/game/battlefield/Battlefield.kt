package com.seancheey.game.battlefield

import com.seancheey.game.model.Node
import java.io.Serializable


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Battlefield : Serializable {
    var name: String
    val width: Double
    val height: Double
    val nodes: ArrayList<Node>
}