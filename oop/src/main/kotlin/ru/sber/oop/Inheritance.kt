package ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    open fun load() = "Nothing much to see here..."

    fun default(): Room {
        return Room( "Bedroom", 100)
    }

}

class TownSquare(): Room("Town Square", 1000){

    override val dangerLevel = super.dangerLevel - 3
    override fun load() = "King $name"

}
