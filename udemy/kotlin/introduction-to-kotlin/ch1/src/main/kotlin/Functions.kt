package KotlinSamples.Functions

import KotlinSamples.Fundamentals.germany

fun printGermany() {
    println(germany())
}

fun main(args: Array<String>) {
    //printGermany()
    //println(triple(7))
    //println(tripleSimpleSyntax(6))
//    println(calcWages(5, 16))
    //println(calcWagesDefault( 10))

    // Named arguments
    println(calcWagesDefault(rate = 22))

    // Var args
    println(sum_up(2,4,5,1))

    println(nestedFun(1,5))

    val(m, n) = anotherDestructuringExample()
    println("destructured pair: $m $n")

    // Infix
    val a = 10.8
    val b = 12.7
    println("Avg is ${a averagedWith b}")
}

fun triple(x:Int): Int {
    return 3*x
}

fun tripleSimpleSyntax(x:Int) = 3*x

fun calcWages(hours:Int, rate:Int) = hours * rate
fun calcWagesDefault(hours:Int = 100, rate:Int = 15) = hours * rate

fun sum_up(vararg values:Int): Int {
    return values.sum()
}

fun nestedFun(x: Int, y: Int): Int {
    // innerFun scoped to nestedFun, not visible outside of it.
    fun innerFun(u:Int, v:Int): Int = u+v
    // Access to nestedFun's scope:
    fun doubleLeft(): Int = 2*x
    return innerFun(doubleLeft(), y) * 4
}

fun anotherDestructuringExample():Pair<Int, Int> {
    return Pair(5, 9)
}

// Extension
infix fun Double.averagedWith(other:Double):Double {
    return (this + other) / 2.0
}
