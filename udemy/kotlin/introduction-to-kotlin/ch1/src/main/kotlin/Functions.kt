package KotlinSamples.Functions

import KotlinSamples.Hello.germany

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