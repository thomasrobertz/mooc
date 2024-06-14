//https://www.codewars.com/kata/554e4a2f232cdd87d9000038

fun makeComplement(dna : String) : String {
    return dna.map { when(it) {
        'G' -> 'C'
        'A' -> 'T'
        'T' -> 'A'
        'C' -> 'G'
        else -> it
    } }.joinToString("")
}

println(makeComplement("TAACG"))