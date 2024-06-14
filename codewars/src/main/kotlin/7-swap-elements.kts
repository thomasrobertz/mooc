// https://www.codewars.com/kata/5886c6b2f3b6ae33dd0000be

// Modified from a course I did
fun IntArray.swap(index1:Int, index2:Int) : IntArray {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
    return this
}

fun firstReverseTry(arr: IntArray) : IntArray {
    if (arr.size == 0) {
        return intArrayOf()
    }
    return arr.swap(0, arr.size - 1)
}

require(firstReverseTry(intArrayOf(1,2,3,4,5)) == intArrayOf(5,2,3,4,1))