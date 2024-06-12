package KotlinSamples.Interfaces

fun main(a:Array<String>) {
    var c:SomeInterface = Concrete()
    c.noop()
    println(c.name)
    println(c.age)
}

interface SomeInterface {
    fun noop()

    // Interface prop
    val name:String get() = "SomeName"

    // Abstract interface prop, must be implemented
    val age:Int
}

class Concrete : SomeInterface {
    override fun noop() {

    }

    override val age: Int
        get() = 9
}