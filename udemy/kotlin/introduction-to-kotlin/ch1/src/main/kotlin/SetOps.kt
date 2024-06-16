package KotlinSamples.SetOps

import KotlinSamples.Grouping.Person // Pasting the people list adds Person import autmatically

fun main() {
    val w1 = "hello".toCharArray().toList()
    val w2 = "help".toCharArray().toList()

    println(w1.distinct())

    println(w1.intersect(w2))
    println(w1.union(w2))
    println(w1.subtract(w2)) // Difference

    val people = listOf(
        Person("John", 20),
        Person("Jill", 20),
        Person("John", 35)
    )

    println("Distinct by name " + people.distinctBy{it.name}) // Only one John
    println("Distinct by age " + people.distinctBy{it.age}) // No Jill
}