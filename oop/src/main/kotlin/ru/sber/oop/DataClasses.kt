package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    override fun equals(other: Any?): Boolean {
        if (other !is User) return false

        val out = this.name == other.name && this.age == other.age

        if (!out) {
            return false
        }

        val thisHaveCity = this::city.isInitialized
        val otherHaveCity = other::city.isInitialized

        if (thisHaveCity != otherHaveCity){
            return false
        }

        if (thisHaveCity && city != other.city) {
            return false
        }

        return out
    }

}

fun main() {
    val user1 = User("Alex", 13)
    user1.city = "Omsk"
    val user2 = user1.copy(name = "Alec")
    val user3 = user1.copy()
    user3.city = "Tomsk"

    println(user1 == user3)
}
