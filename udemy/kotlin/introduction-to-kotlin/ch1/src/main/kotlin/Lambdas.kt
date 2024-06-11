package KotlinSamples.Lambdas;

fun main(args: Array<String>) {
	val product = { x: Int, y: Int -> x * y }
	println(product(2, 3))

	// Alternative lambda definition
	val declaredProduct: (Int, Int) -> Int = { x, y -> x * y }
	println(declaredProduct(8, 2))

	val numbers = listOf(7, 1, 45, 3, 4)
	//val n = numbers.count({ x -> x > 3 }) // Also OK
	//val n = numbers.count{ x -> x > 3 } // Also OK
	val n = numbers.count{ it > 3 } // kotlin way
	println(n)

	var sum = 0
	// Outer scope accessible (Closure)
	numbers.forEach{sum += it}
	println("the sum of $numbers is $sum")

	// HOF (Predicate)
	var maxVal = max(numbers, {x,y -> x<y})
	println("max is $maxVal")

	// Receivers
	val increaseBy = fun Int.(v:Int) = this + v
	var x = 1
	println("$x + 3 = ${x.increaseBy(3)}")

	// Functors
	val a = Average()
	val avg = a(3.0, 4.0, 2.5)
	println("Average is $avg")

	// Add function to "foreign" class:
	operator fun String.invoke():String {
		return "(in words " + this + ")"
	}
	val two = "two"
	println("I have 2 ${two()} apples")
}

fun<T> max(xs:Collection<T>, less: (T,T) -> Boolean):T? {
	var max:T? = null
	for(x in xs)
		if(max == null || less(max, x))
			max = x
	return max
}

class Average {
	operator fun invoke(vararg values:Double):Double {
		var sum = 0.0
		for(x in values)
			sum += x

		return sum / values.size
	}
}