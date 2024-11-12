package KotlinSamples.Enums

enum class Direction {N,S,E,W}
/* Without examples
enum class Color(val rgb:Int) {
    Red(0xff0000),
    Green(0x00ff0),
    Blue(0x0000ff)
}
*/
enum class Color(val rgb:Int) {
    Red(0xff0000) {
        override fun example(): String {
            return "blood"
        }
    },
    Green(0x00ff0)
    {
        override fun example(): String {
            return "grass"
        }
    },
    Blue(0x0000ff) {
        override fun example(): String {
            return "sky"
        }
    };

    abstract fun example(): String
}

fun main() {
    val d = Direction.E
    println(d)

    val b = Color.Blue
    println("b has name ${b.name} value = ${b.rgb} and ord ${b.ordinal}")
    println("$b is color of ${b.example()}")

    for (c in Color.values()) {
        println(c)
    }

    println("Value of red is ${Color.valueOf("Red").rgb}")
}