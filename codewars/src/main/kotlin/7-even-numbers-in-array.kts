// https://www.codewars.com/kata/5a431c0de1ce0ec33a00000c

fun evenNumbers(array: List<Int>, number: Int): List<Int> {
    return array
            .filter { it % 2 == 0 }
            .takeLast(number)
            .toList()
}

println(evenNumbers(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 3))