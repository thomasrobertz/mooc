// https://www.codewars.com/kata/57cebe1dc6fdc20c57000ac9

fun findShort(s: String): Int {
    return s.split(" ").map{ it.length }.min()
}

// Other solution
// fun findShort(s: String): Int = s.split(" ").minOf{it.length}