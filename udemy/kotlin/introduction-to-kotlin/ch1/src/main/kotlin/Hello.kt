fun main(args:Array<String>) {
    println("Hello Kotlin")
    var_declarations()
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