package KotlinSamples.Companion

import KotlinSamples.Singleton.Point
import kotlin.math.cos
import kotlin.math.sin

fun main(a:Array<String>) {
    var p = PointC(8.9, 3.5) // Standard

    var factoryPoint = PointC.newCartesianPoint(4.5, 5.6)

    // Could also reference by Companion explicitly, but that is redundant in this case
    PointC.Companion.newPolarPoint(1.2, 3.4)
}

class PointC(var x:Double, var y:Double) {
    // Companion is like an inner static class that can reference the containing class
    companion object {
        fun newCartesianPoint(x:Double, y:Double): PointC {
            return PointC(x, y)
        }
        fun newPolarPoint(rho:Double, theta:Double): PointC {
            return PointC(rho * cos(theta), rho * sin(theta))
        }
    }
}

// Can also name the companion object
class NamedCompanion {
    companion object F { }
}

// Can make the companion implement a contract
interface MyCompanion<T> {
    fun doSomething(): T
}
class ContractCompanion {
    companion object : MyCompanion<Point> {
        override fun doSomething(): Point {
            return Point(4.5,5.0)
        }

    }
}
