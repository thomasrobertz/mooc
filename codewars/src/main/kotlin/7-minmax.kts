// https://www.codewars.com/kata/554b4ac871d6813a03000035

fun highAndLow(numbers: String): String {
    val numberList = numbers.split(" ").map { it.toInt() }
    return "${numberList.max()} ${numberList.min()}"
}

highAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4")