//https://www.codewars.com/kata/5899dc03bc95b1bf1b0000ad

val input1 = intArrayOf()
val input2 = intArrayOf(0)
val input3 = intArrayOf(-1,-2,-3,-4,-5)

fun invert(arr: IntArray): IntArray {
    return arr.map { it * -1 }.toIntArray()
}

println(invert(input1).toList())
println(invert(input2).toList())
println(invert(input3).toList())