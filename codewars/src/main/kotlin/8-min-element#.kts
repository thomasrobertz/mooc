import java.util.*
import java.util.function.Function

//https://www.codewars.com/kata/55a2d7ebe362935a210000b2

fun findSmallestInt(nums: List<Int>): Int {
    return nums.stream().min(Comparator.naturalOrder()).get();
}

require(findSmallestInt(listOf(15, 20, 10, 17, 22, 9001)) == 10)