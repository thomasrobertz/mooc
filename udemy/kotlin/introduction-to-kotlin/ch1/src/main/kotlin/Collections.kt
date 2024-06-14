package KotlinSamples.Collections

fun main(/*args not necessary*/) {
    //simpleGenerator()
    //simpleSequenceWithPredicate()
    //maps()
    //flatMaps()
    //aggregates()
    folds()
}

fun folds() {
    val gen = generateSequence(1, { it + 1 })
    val numbers = gen.take(5).toList()

    val i = 1000
    println("sum (fold) = ${
        numbers.fold(i, {x, p -> x + p})
    }")

    // ax^2 + bx + c
    // 1 * 3^2 + 2*3 + 3 = 18
    val base = 3.0
    println("poly = ${
        numbers.foldIndexed(0) { i, p, c ->
            val xp = Math.pow(base, (2 - i).toDouble()).toInt()
            println("i = $i \t p = $p \t c = $c \t xp = $xp")
            c * xp + p // Last statement is automatically returned
        }
    }")

    // 1,2,3 -> it ^ 2
    // ((1 ^ 2 + 2) ^ 2 + 3) ^ 2 = 144
    // ((3 ^ 2 + 2) ^ 2 + 1) ^ 2 = 14884
    println("${numbers.take(3).fold(0, {p, x -> (p + x) * (p + x)})}")
    println("${numbers.take(3).foldRight(0, {p, x -> (p + x) * (p + x)})}")
}

fun aggregates() {
    val gen = generateSequence(1, { it + 1 })
    val numbers = gen.take(5).toList()

    // Be careful, not every method expects a HOF
    println(numbers.joinToString ( "->" )) // Good
    println(numbers.joinToString { "->" }) // Wrong

    // Reduce
    /*
    *   R 1 + 2 (3)
        R 3 + 3 (6)
        R 6 + 4 (10)
        R 10 + 5 (15)
        sum = 15
    * */
    println("sum = ${
        numbers.reduce{x, p ->      // Idea is for p to stand for "partial result
            println("R $x + $p")
            x + p
        }
    }")

    /*
        RR 4 + 5    (9 <- partial result)
        RR 3 + 9    (12)
        RR 2 + 12   (14)
        RR 1 + 14   (15)
        sum = 15
    * */

    println("sum = ${
        numbers.reduceRight{x, p -> 
            println("RR $x + $p")
            x + p
        }
    }")

    println("R prod = ${
        numbers.reduce{x, p ->
            println("RP $x * $p")
            x * p}
    }")

    println("RR prod = ${
        numbers.reduceRight{x, p ->
            println("RRP $x * $p")
            x * p}
    }")

}

fun flatMaps() {
    val seq = listOf("r,g,b", "orange", "b,w")
    var allWords = seq.flatMap { it.split(",") }
    println(allWords.toList())

    val objects = arrayOf("a", "b", "c")
    val colors = arrayOf("a", "b", "c")

    // Bubble
    val pairs : List<String> = objects.flatMap { o -> colors.map {
        c -> "$o $c"
    } }

    println(pairs)
}

fun maps () {
    val gen = generateSequence(1, { it + 1 })
    val numbers = gen.take(4)

    val sq = numbers.map { it * it }
    println(sq.toList())

    val words = "This is a sentence"
    val wlength = words.split(' ').map { it.length }
    println(wlength.toList())

    // Map to object
    val wordsWithLength =  words.split(' ').map { object {
        val word = it
        val length = it.length
    }}

    for (wl in wordsWithLength) {
        println("Length of '${wl.word}' is ${wl.length}")
    }

    // Similar mapping to an "Associate"
    val wordLengthPairs = words.split(' ').associate { it.to(it.length) }

    for (wl in wordLengthPairs) {
        println(wl)
    }
}

fun simpleSequenceWithPredicate() {
    val numbers: Sequence<Int> = arrayOf(1,2,3,4,5,6,7,8).asSequence()
    println(numbers.all { it > 0 });
    println(numbers.any { it > 7 });
    println(numbers.filter { it > 4 }.toList());
    println(numbers.filter { it % 2 == 0 }.toList());
    println(numbers.contains(4));
    println(numbers.count{it > 2});

    println(emptyArray<Int>().any())

}

fun simpleGenerator(): Unit {
    val gen = generateSequence(1, { it + 1 })
    val numbers = gen.take(10)
    println(numbers.toList())
}