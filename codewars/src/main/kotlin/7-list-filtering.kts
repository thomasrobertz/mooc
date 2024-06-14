// https://www.codewars.com/kata/53dbd5315a3c69eed20002dd

fun filterList(l: List<Any>): List<Int> {
    return l.filter{ it is Int }.map{ it.toString().toInt() }.toList()
}

println(filterList(listOf(1, 2, 'a', 'b')))