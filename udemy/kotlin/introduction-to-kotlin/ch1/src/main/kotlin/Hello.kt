fun main(args:Array<String>) {
    println("Hello Kotlin")
    //var_declarations()
    ranges()
}

fun ranges() {
    val a:IntRange = 1..10  // Closed: Includes 1 and 10
    for (x in a) println(x)

    val b = 1.rangeTo(10) // Same as above
    for (x in b) println(x)

    val c = b.reversed() // Is now an IntProgression
    println("Sum of $c = ${c.sum()}")

    // From the above we get a hint why we cant do 10..1 (Like we neither can in haskell)
    // Instead we need to do this:
    val d = 10.downTo(1)
    for(x in d) println(x)

    // kotlin syntax: omit dot and parenthesis
    var f = 10 downTo 1

    // steps in ranges
    var g = 30 downTo 1 step 3
    for(x in g) println(x)

    //b.forEach(x -> println(x))

}

fun var_declarations() {

    // val
    // ---

    val a:Int = 64
    val b:Long = 123
    val c:Float = 3.2f
    val d:Double = 12.3
    val e:Double = 5.4e2

    val g:Int
    g = 78
    // g = 89 // Error:  Kotlin: Val cannot be reassigned

    println("$a $b $c $d $e $g")

    //val f:StringBuffer = StringBuffer("test")
    // Type StringBuffer can be inferred:
    val f = StringBuffer("test")
    f.replace(0, 1, "T")
    println(f)

    // var
    // ---

    // var h:Int = 56
    // Here also the type is redundant b/c it can be inferred:
    var h = 56
    h = 67
    println(h)
}