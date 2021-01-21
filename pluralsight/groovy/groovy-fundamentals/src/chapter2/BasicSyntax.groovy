package chapter2

System.out.println("Hello, world: Java Style");
System.out.println("Hello, world: No semicolon needed")
println("Hello, world: All standard packages are present in the groovy RT");
println "Hello, world: No parentheses are needed (Except sometimes, e.g. for loop)"

/* Collections */
def beatles = ["John", "Paul", "George", "Peter"]

def greeting = "Hi"
for (int i = 0; i < beatles.size(); i++) { // We could leave off the parenthesis on size() but I don't like that

    // Groovy style: $simple and ${evaluated} interpolation.
    // $ is also called the "g-string" in groovy
    println "$greeting ${beatles[i]}"
}

// More groovy like: for - in
for(beatle in beatles) {
    println "$greeting $beatle"
}

/* Ranges */
def numbers = 1..10
for (number in numbers) {
    print number
}
println " "

def letters = "a".."d"
for (letter in letters) {
    print letter
}
println " "

// Range in Enums
enum DAYS {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

def weekdays = DAYS.MON..DAYS.SUN
for (day in weekdays) {
    println day
}

// Extents of an enum:
println "Extents: " + weekdays.from + " to " + weekdays.to

/* Functions */
// Can use the def keyword to define a function
def isEven(def number) {

    //return number % 2 == 0

    // Since groovy uses implicit return, we can omit the return keyword:
    number % 2 == 0
}

for (number in numbers) {
    if(isEven(number)) {
        println "Even: $number"
    }
}

/* Closures */

// Define a closure:
def myClosure = { println "I am a block of code in an anonymous function" }
myClosure()

// Closures are lazy and don't get executed until called:
def deferredClosures = {
    println "I am once (again) executed at: " + new Date()
    sleep(1000)
}
for(i in 1..3) {
    deferredClosures()
}

// The difference between a function and a closure can be intuitively understood
// by looking at how they are used (As compared to: how they're defined)
(1..3).each({
    println "In closure $it"
})

// Define a closure parameter j instead of $it:
(1..3).each({j ->
    println "In closure $j"
})

// Closures can bring brevity.
// In the findAll closure, remember that return is implicit! And so can be omitted.
// Also remember that we can omit most parentheses, which we do here around the methods
// findAll() and each()
(1..10).findAll {it % 2 == 0 } .each {println "Even: $it" }

/* Dynamic Typing */
def cat = "meow"
def one = 1

// What types are inferred?
println cat.getClass()
println one.getClass()

// We can also be explicit:
Integer two = 2

// But then we are strongly typed:
//Integer two = "this wont work"


println cat.toUpperCase()
// Also, since one has been (duck-) typed, it's now an integer
//println one.toUpperCase() // Will throw an error
// !!! This is not yet done at compile time (groovyc)!

// However since it was ducktyped, we can re-type it:
one = "one"
println one.toUpperCase()

/* So to summarize:

1. Use static typing same as in Java.
2. Duck typing stays (semi-statically) typed as long as not re-typed.
3. No compile-time type checking! Important to unit test!!

*/

