fun main(args:Array<String>) {
    println("Hello Kotlin")
    var_declarations()
}

fun var_declarations() {
    val a:Int = 64
    val b:Long = 123
    val c:Float = 3.2f
    val d:Double = 12.3
    val e:Double = 5.4e2

    val g:Int
    g = 78

    println("$a $b $c $d $e $g")

    //val f:StringBuffer = StringBuffer("test")
    // Type StringBuffer can be inferred:
    val f = StringBuffer("test")
    f.replace(0, 1, "T")
    println(f)
}