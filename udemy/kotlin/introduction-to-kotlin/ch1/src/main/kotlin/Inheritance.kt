package KotlinSamples.Inheritance

fun main(a: Array<String>) {
    var j = Person("John")
    var a = Manager("Alfred", listOf(j))

    j.talk()
    // In this commit, Manager doesn't have talk()
    a.talk()
}

class Person(var name:String) {
    fun talk() {
        println("I am $name.")
    }
}

class Manager(var name:String, var subordinates:List<Person>) {

}
