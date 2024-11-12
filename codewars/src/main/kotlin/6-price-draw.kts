//https://www.codewars.com/kata/5616868c81a0f281e500005c

package Rank

val BASE: Int = 96

fun Char.toNormalizedCode() : Int {
    return this.lowercase().toCharArray()[0].code - BASE
}

fun nthRank(st: String, we: IntArray, n: Int): String {
    if (st.isEmpty()) {
        return  "No participants"
    }
    val participants = st.split(",")
    if (n > participants.size) {
        return "Not enough participants"
    }
    val rank = mutableMapOf<String, Int>()
    var i = 0
    participants.forEach {
        rank.put(it, it.fold(it.length) { i, a -> i + a.toNormalizedCode()} * we[i])
        i++
    }
    return rank.entries
        .sortedWith(compareByDescending<Map.Entry<String, Int>> { it.value }
            .thenBy { it.key })
        .get(n - 1).key
}