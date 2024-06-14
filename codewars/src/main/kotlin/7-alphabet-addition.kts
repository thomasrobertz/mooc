// https://www.codewars.com/kata/5d50e3914861a500121e1958

val BASE: Int = 96
val ALPHABET_LENGTH: Int = 26

fun Char.toNormalizedCode() : Int {
    return this.code - BASE
}

fun Int.normalizedCodeToChar(): Char {
    return ((this - 1) % ALPHABET_LENGTH + 1 + BASE).toChar()
}

fun addLetters(arr: List<Char>): Char {
    return if(arr.isEmpty()) { 'z' } else {
        return arr.sumOf { it.toNormalizedCode() }.normalizedCodeToChar()
    }
}

require(addLetters(listOf('a', 'b', 'c')) == 'f')
require(addLetters(listOf('a', 'z')) == 'a')
require(addLetters(listOf('y', 'c', 'b')) == 'd')

// Other Solutions

// fun addLetters(arr: List<Char>) = 'z' - arr.sumBy { 'z' - it } % 26

// fun addLetters(arr: List<Char>): Char =
//    arr.fold('z') { acc, c ->
//        val sum = acc + (c - '`')
//
//        if (sum > 'z') 'a' + (sum - '{')
//        else sum
//    }