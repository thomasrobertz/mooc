package KotlinSamples.Inheritance

fun main(a: Array<String>) {
    var j = Person("John")
    var a = Manager("Alfred", listOf(j))

    j.talk()
    // In this commit, Manager doesn't have talk()
    a.talk()
}

// Classes (and functions) are final by default,
// to make them inheritable, add "open"
open class Person(var name:String) {
    fun talk() {
        println("I am $name.")
    }
    open fun overrideMe() {

    }
}

// Inherit from Person, and it is mandatory to call the parent cons, we
// do this here with Person(mName)
// We can't use "name" because that hides the supertype param.
// We could add "override" to the field, but just as well rename as we did here.
class Manager(var mName:String, var subordinates:List<Person>) : Person(mName) {
    override fun overrideMe() {
        super.overrideMe()
    }
}
