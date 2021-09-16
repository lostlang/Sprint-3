package ru.sber.oop

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = (1..10).random()

    fun attack(opponent: Fightable): Int
}

class Player(var name: String, var isBlessed: Boolean,
             override val powerType: String, override var healthPoints: Int): Fightable {
    override fun attack(opponent: Fightable): Int {
        val damage = if (this.isBlessed) this.damageRoll * 2 else this.damageRoll
        opponent.healthPoints -= damage
        return damage
    }

}

abstract class Monster(): Fightable {
    abstract val name: String
    abstract val description: String

    override fun attack(opponent: Fightable): Int {
        val damage = this.damageRoll
        opponent.healthPoints -= damage
        return damage
    }
}

class Goblin(override val name: String, override val description: String,
             override val powerType: String, override var healthPoints: Int) : Monster(){
    override val damageRoll: Int
        get() = super.damageRoll / 2
}

fun Monster.getSalutation() = "Hello, I'm ${this.name}"
