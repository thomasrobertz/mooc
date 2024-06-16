package KotlinSamples.Partition

fun main() {
    val seq = arrayOf(-2, -1, 0, 1, 2)
    val (neg, pos) = seq.partition { it < 0 }
    println(neg)
    println(pos)

    val numbers = arrayOf(4, 4, 2, 2, 1, 1, 2, 2, 3, 3)
    println(numbers.drop(2)) // Removes the leading 3s
    println(numbers.drop(2).take(6)) // Removes the leading 3s and only takes 6 numbers

    // Behaviour similar to Haskell
    println(numbers.take(100))
    println(numbers.take(0))
    println(emptyArray<Int>().take(100))

    // Only operates on the beginning of the array
    println(numbers.takeWhile { it > 1 })
    println(numbers.dropWhile { it == 3 })

    println(numbers.dropLast(4)) // Drops 2,2,3,3
}