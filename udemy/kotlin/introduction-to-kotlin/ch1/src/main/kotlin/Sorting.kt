package KotlinSamples.Sorting

import KotlinSamples.Grouping.Person
import kotlin.comparisons.compareBy
import java.util.Random

fun main() {
    val rand = Random()
    val randomValues = generateSequence { rand.nextInt(10) - 5 }.take(10).toList()

    println(randomValues)
    println(randomValues.sorted())
    println(randomValues.sortedDescending())

    val people = listOf(
        Person("Adam", 36),
        Person("Boris", 18),
        Person("Claire", 36),
        Person("Adam", 20),
        Person("Jack", 20),
    )

    println(people)
    println(people.sortedBy { it.name })

    // Sort by multiple parameters
    println(people.sortedWith (compareBy(Person::age, Person::name)))
    println(people.sortedWith(compareBy({it.name}, {it.age}))) // Adam{20} before Adam{36}
    println(people.sortedWith(compareBy<Person>{it.age}.thenByDescending({it.name})))
}