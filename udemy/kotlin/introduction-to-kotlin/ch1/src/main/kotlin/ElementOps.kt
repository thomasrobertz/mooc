package KotlinSamples.ElementOps

fun main() {
    val numbers = listOf(1, 2, 3)
    println("first is ${numbers.first()}")
    println("first > 2 is ${numbers.first({it > 2})}")

    //println("first > 10 is ${numbers.first({it > 10})}") // Exception
    println("first > 10 is ${numbers.firstOrNull({it > 10})}")
    // Can also use last()

    // Single
    val numbersWithSingle = listOf(1)
    println(numbersWithSingle.single())
    //println(numbers.single()) // Exception
    println(numbers.singleOrNull()) // Similar to above

    println(numbers.single({it == 3}))

    println("El @1: ${numbers.elementAt(1)}")
    println("El @1: ${numbers.elementAtOrNull(10)}")
    println("El @1: ${numbers.elementAtOrElse(10, {-it})}")
}