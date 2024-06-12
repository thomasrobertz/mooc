package KotlinSamples.Classes

fun main(args:Array<String>) {
    val me = Person()
    me.name = "Alex"
    me.age = 34

    println("${me.name} can ${if (!me.canVote) "not" else ""}vote.")

    // Extension function
    var l:ArrayList<String> = arrayListOf("a", "b", "c", "d")
    l.swap(1,3)
    println(l)

    // Extension function 2
    var ns:String? = null
    ns.print()
    ns = "y"
    ns.print()

    // Extension property
    ns = null
    println("String is ${if (ns.empty) "empty" else "not empty"}")
    ns = "444"
    println("String is ${if (ns.empty) "empty" else "not empty"}")

    val h = Human("Dmitri", 44)

    val hp = HumanWithStoredParams("Dmitri", 44)
    println("Human ${hp.name} is ${hp.age} yo")
    hp.age = 45
    // hp.name = "Paul" // Error

}

class Person {
    // Analogous to Java class members:
    //   These are called "fields" or "backing field"
    //   Usually non-nullable in kotlin
    //   vars if we want to change them later
    var name: String = ""
    var age:Int = -1

    // Read only field with getter
    val canVote: Boolean
        get() = age >= 16

    // Getter/Setter
    var ssn = "0000001"
        get() = field
        set(value) {
            field = value
        }
}

// Extension function .swap on ArrayList.
// Here, ArrayList<T> is the so-called receiver.
fun <T> ArrayList<T>.swap(index1:Int, index2:Int) {
    // "this" refers to the receiver.
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

// Extension function Example 2: Nullable print
// Any will act on all objects.
fun Any?.print() {
    if(this == null) println("Object is null")
    else println("Object is ${this.toString()}")
}

// Extension Property (not: Function)
val String?.empty : Boolean
    get() = (this == null || this.length == 0)

// Primary Cons with init block
class Human(name:String, age:Int) {
    init {
        println("Human initialized with $name and $age")
    }
}

// Primary Cons with init block, storing parameters as vals
class HumanWithStoredParams(val name:String, var age:Int) {
}