// https://www.codewars.com/kata/515e271a311df0350d00000f

fun squareSum(n: Array<Int>): Int {
    return n.sumBy{ it * it }
}

require(squareSum(arrayOf(0, 3, 4, 5)) == 50)
require(squareSum(arrayOf()) == 0)