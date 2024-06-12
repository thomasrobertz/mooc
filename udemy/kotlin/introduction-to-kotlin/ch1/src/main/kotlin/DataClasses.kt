package KotlinSamples.DataClasses

fun main(a:Array<String>) {
    val h = Human("Jill", 22)
    println(h) // Prints sth like 'HumanWithStoredParams@58372a00'

    val h2 = Human("Jill", 22)
    println(h == h2) // False

    // How do address this? Use data class
    val dh = HumanDataClass("James", 55)
    val dh2 = HumanDataClass("James", 55)
    println(dh)
    println(dh == dh2)

    // Also we get copying
    val dh3 = dh.copy()
    println(dh == dh3) // true
    // But, deep equal
    println(dh === dh3) // false

    // And destructuring:
    val(name, age) = dh
    println("Destructured: $name, $age")

}

class Human(val name:String, var age:Int) {
}

data class HumanDataClass(val name:String, var age:Int) {
    // data adds :
    //   equals
    //   hashCode
    //   toString (can still be overridden with toString())
    //   copy
    // and destructuring.
}