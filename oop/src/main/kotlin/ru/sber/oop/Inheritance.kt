package ru.sber.oop

open class Room(val name: String, val size: Int) {
    protected open val dangerLevel = 5
    var enemy: Monster = Goblin("Flin", "bed joke", "fire", 50)

    fun description() = "Room: $name"

    open fun load() = this.enemy.getSalutation()

    constructor(name: String): this(name, 100)
}

class TownSquare(): Room("Town Square", 1000){
    override val dangerLevel = super.dangerLevel - 3
    
    final override fun load() = "King $name"
}