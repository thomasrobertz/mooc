package KotlinSamples.Singleton

/* Before
class PointFactory {
    fun newCartesianPoint(x:Double, y:Double): Point {
        return Point(x, y)
    }
    fun newPolarPoint(rho:Double, theta:Double): Point {
        return Point(rho * Math.cos(theta), rho * Math.sin(theta))
    }
}
*/
object PointFactory {
    fun newCartesianPoint(x:Double, y:Double): Point {
        return Point(x, y)
    }
    fun newPolarPoint(rho:Double, theta:Double): Point {
        return Point(rho * Math.cos(theta), rho * Math.sin(theta))
    }
}

fun main(a:Array<String>) {
    //var pf = PointFactory() // Before
    var p = PointFactory.newCartesianPoint(3.0, Math.PI/2)
}

class Point(var x:Double, var y:Double) {
}