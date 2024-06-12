package KotlinSamples.Delegation

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main(args:Array<String>) {
    var p = Person()
    p.name = "John"
    println("name is ${p.name}")

    // Lazy initialization
    println(p.lazyVal)
    println(p.lazyVal)
    println(p.lazyVal)

    // Prop observer
    p.obsName = "Frank"
    p.obsName = "Jack"
}

class PropertyDelegate {
    operator fun getValue(thisRef:Any?, property: KProperty<*>): String {
        println("$thisRef delegating ${property.name}")
        return property.name
    }
    operator fun setValue(thisRef:Any?, property: KProperty<*>, value:String): String {
        println("$value assigned by delegation to ${property.name} in $thisRef")
        return value
    }
}

class Person {
    var name: String by PropertyDelegate()

    // Doesn't have to be inside a class
    val lazyVal: String by lazy {
        println("Lazy init")
        "test"
    }

    // Property observer
    var obsName: String by Delegates.observable("Peter") {
        property, oldValue, newValue -> println("$oldValue -> $newValue")
    }
}