package KotlinSamples.OperatorOverloads

// In kotlin we can override operators, but only the standard ones (See kotlin docs).

data class Vector(var x:Int, var y:Int) {
    // plus() is a kotlin standard operator function
    operator fun plus(otherVector: Vector) : Vector {
        return Vector(x + otherVector.x, y + otherVector.y)
    }
}

fun main() {
    var v1 = Vector(3, 4)
    var v2 = Vector(3, 3)

    println(v1 + v2)
}