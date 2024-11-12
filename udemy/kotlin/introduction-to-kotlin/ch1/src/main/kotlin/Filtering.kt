package KotlinSamples.Filtering

fun main() {
    val seq = generateSequence(1, {it + 1})
    val numbers = seq.take(10).toList()
    val evens = numbers.filter { it % 1 == 0 }
    println(evens)

    val notBy3 = numbers.filterNot { it % 3 == 0 }
    println(notBy3)

    val oddSquares = numbers.map { it * it }.filter { it % 2 == 0 }
    println(oddSquares)

    // Heterogeneous arrays
    val values = arrayOf<Any>(1, 2.5, 3, 4.56)
    println(values.filter { it is Int })

    /// Notice the ?
    val moreValues  = arrayOf<Int?>(1, 2, 3, null, 5)
    println(moreValues.filterNotNull())
}