package com.seancheey.game

/**
 * Created by Seancheey on 20/05/2017.
 * GitHub: https://github.com/Seancheey
 */


class Block(name: String, imageURL: String, width: Int, height: Int, val health: Int) : Component(name, imageURL, width, height) {
    constructor() : this("default", "file:dat/cube.png", 10, 10, 10)
}
