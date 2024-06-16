// https://www.codewars.com/kata/55bf01e5a717a0d57e0000ec

fun persistence(num: Int) : Int {
    var counter = 0
    var mutable = num.toString()
    while(mutable.length > 1) {
        counter++
        mutable = mutable.map{ it.digitToInt() }.reduce{ i, a -> i * a }.toString()
    }
    return counter
}
