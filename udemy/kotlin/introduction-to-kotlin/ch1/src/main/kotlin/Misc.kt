package KotlinSamples.Misc

// Type aliases
typealias FloatSet = Set<Float>
typealias MapWithStringKeys<T> = Map<String, T>

fun main() {
    val f: FloatSet = setOf(1.2f, 3.4f)
    val m: MapWithStringKeys<Int> = mapOf("h" to 5)
}

typealias Predicate<T> = (T) -> Boolean

fun<T> where(items:Sequence<T>, p:Predicate<T>) : Sequence<T> {
    return items.filter{ x -> p(x) }
}

//
typealias PropertyChangeHandler = (Object, String) -> Unit

//
class Bike {
    inner class Wheel {

    }
}
typealias BikeWheel = Bike.Wheel