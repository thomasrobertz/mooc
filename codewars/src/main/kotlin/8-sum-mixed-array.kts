// https://www.codewars.com/kata/57eaeb9578748ff92a000009

val input = listOf(9, 3, "7", "3")

fun sum(mixed: List<Any>): Int {
    return mixed.map { it.toString().toInt() }.sum();
}

println(sum(input))