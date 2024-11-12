package KotlinSamples.Reflection

import KotlinSamples.Grouping.Person
import java.lang.reflect.Field
import java.lang.reflect.Method
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

open class Base(x:Int)
class Derived(x:Int) : Base(x)

fun process(b:Base) {
    if (b is Derived) {
        println(b::class.qualifiedName)
    }
}

var x = 1
var y = 22

class Acme(var id:Int)

val String.lastChar : Char
    get() = this[length - 1]

class Something
fun<T> doSomething(generator: () -> T) {
    val x: T = generator()
    println("x is " + x.toString())
}

fun main() {
    // kotlin Reflection includes reflection capabilities for advanced features like Singelton, Companions, Extension functions,...

    val c: KClass<Person> = Person::class
    println(c.qualifiedName)
    println(c.members.map { it.name })

    println("Is Companion: ${c.isCompanion}")

    // Use java reflection
    var j = c.java
    println(j.simpleName)

    // Inheritance, Smart Typing
    var z = Derived(234)
    process(z) // Derived not Based, because of smart typing: in if (b is Derived) {... // b is now a "Derived" type

    // Functions and Reflection
    var numbers = generateSequence(1, { it + 1 }).take(5)

    println(numbers.filter(::isOdd).toList())

    // Specifiying type (Looks a bit like a Haskell type definition)
    val predicateS: (String) -> Boolean = ::isOdd
    val predicateI: (Int) -> Boolean = ::isOdd

    // Compose f(g(x))
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    val strings = "this is a fun experiment".split(' ')
    val oddLength = compose(::isOdd, String::length)
    println("Composed f g " + strings.filter(oddLength))

    // Properties
    val a: KMutableProperty0<Int> = ::x
    val b: KProperty0<Int> = ::y

    println(a.get())
    a.set(123)

    println(b.get())
    //b.set(567) // Not possible, because not mutable

    val acme: Acme = Acme(45)
    val pid = Acme::id
    println(pid.get(acme))

    // Java reflection interop
    val javaGetter: Method? = Acme::id.javaGetter
    val file: Field? = Acme::id.javaField

    // Extension properties
    val ls = String::lastChar
    println("lastChar " + ls.get("hello"))

    // Reference to Cons
    doSomething(::Something)

    // Bound References
    val r: Regex = "\\d+".toRegex()
    val generalRef = Regex::matches
    // Standard way with receiver
    println("Matches: " + generalRef(r, "123"))

    // Reflection way, bound
    val isNumber = r::matches
    println("Matches: " + isNumber("123"))

    // Another example
    val strings2 = listOf("foo", "bar", "123")
    println(strings2.filter(isNumber))

    // Another Prop ref, no receiver needed, because autobound
    val lengthABC: KProperty0<Int> = "ABC"::length
    print("Autbound: " + lengthABC.get())
}

fun isOdd(x:Int) = x%2!=0
fun isOdd(s:String) = s == "weird"
