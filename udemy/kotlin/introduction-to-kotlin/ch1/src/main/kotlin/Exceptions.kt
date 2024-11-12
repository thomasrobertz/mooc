package KotlinSamples.Exceptions

import java.lang.NumberFormatException

fun main() {
    val v = arrayOf(1,2,3)

    try {
        println(v[10])
    } catch(e:ArrayIndexOutOfBoundsException) {
        println("Exc: " + e.toString())
    } finally {
        println("Cleanup")
    }

    // Excpetion handling as expression
    var text = "123"
    val number:Int? = try { text.toInt() } catch(e:NumberFormatException) { null }
    println(number)

    text = "12f3"
    val number2:Int? = try { text.toInt() } catch(e:NumberFormatException) { null }
    println(number2)
}