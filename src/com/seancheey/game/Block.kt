package com.seancheey.game

/**
* Created by Seancheey on 20/05/2017.
* GitHub: https://github.com/Seancheey
*/

class Block(name: String, imageURL: String, width: Int, height: Int, health: Int) : Component(name, imageURL, width, height){
    var health = 0;
    init{
        this.health = health
    }
    constructor(): this("deault","file:dat/cube.png",10,10,10)

}