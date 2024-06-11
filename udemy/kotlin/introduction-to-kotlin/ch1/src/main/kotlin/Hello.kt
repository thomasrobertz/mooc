package KotlinSamples.Hello

fun main(args:Array<String>) {
    println("Hello Kotlin")
    //var_declarations()
    //ranges()
    //arrays()
    //chars_and_strings()
    //nullability()
    //if_statement()
    //flow_based_typing()
    //for_loops()
    when_exp()
}

fun when_exp() {
    val code = 49

    // when is like switch case
    when(code) {
        44 -> println("UK")
        46 -> println("Sweden")
        germany() -> println("Germany")
        39, 379 -> println("Vatican")
        in 380..400 -> println("Handled by department B")
        else -> {
            println("Unknown code")
        }
    }

    // Type matching
    val z:Any = 5
    //val z:Any = "5"

    when(z) {
        is Int -> println("It's an int")
        is String -> println("It's a string")
    }

    // It's also an expression
    val q = when(code) {
        46 -> "Sweden"
        germany() -> "Germany"
        else -> { "Invalid" }
    }
    println(q)
}

fun germany(): Any {
    return 49
}

fun for_loops() {
    for(a in (10 downTo 1)) print("$a\t")
    println()

    val ints = intArrayOf(4,3,2,1)
    for((index, value) in ints.withIndex())
        print("$index,$value\t")

    println()

    // Destructuring
    val capitals = mapOf("Paris" to "France", "London" to "UK")
    for((city, country) in capitals)
        println("$city is the capital of $country")
}

fun flow_based_typing() {
    var s:String? = "nlbl"

    // Error: Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
    //s.length

    if(s != null) {
        // In this block s has been null checked, like in Java OptionalisPresent
        // so access to s.length is allowed
        println(s.length)
    }

    // We get a super type that doesn't have the property
    // (Cloneable as super type just used for demonstrative purposes here)
    var x:Cloneable = intArrayOf(1, 2, 3)

    // Cloneable doesnt have size
    //x.size

    if (x is IntArray) {
        // Similar to above, we can now access size
        println(x.size)
    }
}

fun if_statement() {
    val temp = 20
    var feel:String

    // Standard
    if (temp < 10)
        feel = "cold"
    else if (temp >= 20)
        feel = "hot"
    else feel = "OK"

    println("It feels $feel.")

    // if is an expression
    val exprFeel = if (temp < 10)
        "cold"
    else if (temp >= 20)
        "hot"
    else "OK"

    println("It feels $exprFeel")

    println("It feels ${if (temp > 18) "warm" else "cold"}")
}

fun nullability() {
    // Error Kotlin: Null can not be a value of a non-null type String
    //var t: String = null

    var nullableString: String? = null
    println(nullableString) // prints "null"

    var nullableLength: Int? = nullableString?.length
    println(nullableLength) // Also prints "null"

    val defaultLength: Int = nullableString?.length ?: -1
    println(defaultLength) // Prints "-1"

    val weKnowThisWillProbablyHaveAString:String? = "foo"
    println(weKnowThisWillProbablyHaveAString!!.length)
}

fun chars_and_strings() {
    // Single quotes just like in Java
    val a: Char = '\u0001'
    println(a)

    val b: String = "A string"
    println(b)
    println(b[4])
    for (letter in b) println(letter)

    val lit = """
        hello
          this is literal
    """.trimIndent()
    println(lit)

    val price = 123
    // Can use double quotes for strings within ${ }
    println("$price${"$"} at 10% discount = ${price - (price/10)}")
}

fun arrays() {
    //var names:Array<String> = arrayOf("John", "Jane", "Jill")
    // Again, the type can be inferred:
    var names = arrayOf("John", "Jane", "Jill")
    names[0] = "Jack"
    println(names.toList())

    var ages: IntArray = intArrayOf(44, 30 ,27)
    println(ages.toList())

    // Type Double inferred on the array
    var value = Array(10, {2.1})
    println(value.toList())

    // An array of 10 square numbers as strings
    // special name "it" refers to an item in the array
    // Unlike in ranges, we here get an initial array from 0 to 9
    var squares = Array(10, {(it * it).toString()})
    println(squares.toList())

    // Star merge operator
    val toMerge = intArrayOf(2,3,5)
    val oneToSeven = intArrayOf(1,*toMerge,4,6,7)
    println(oneToSeven.toList())
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

    // kotlin syntax: omit dot and parenthesis (not on all declarations though)
    var f = 10 downTo 1

    // steps in ranges
    var g = 30 downTo 1 step 3
    for(x in g) println(x)
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