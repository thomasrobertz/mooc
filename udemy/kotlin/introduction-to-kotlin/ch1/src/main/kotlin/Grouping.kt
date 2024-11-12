package KotlinSamples.Grouping

data class Person(var name:String, var age:Int)

fun main() {

    val people = listOf(
        Person("Adam", 36),
        Person("Boris", 18),
        Person("Claire", 36),
        Person("Adam", 20),
        Person("Jack", 20),
    )

    val byName: Map<String, List<Person>> = people.groupBy(Person::name)
    byName.entries.forEach{e -> println("${e.key} ${e.value}")} // First entry is a group with two Adams

    val byAge: Map<Int, List<Person>> = people.groupBy(Person::age)
    byAge.entries.forEach{e -> println("${e.key} ${e.value}")} // We have two entries with groups of 36 and 20 resp.

    // Grouping by more than one criteria
    // Compare to Java groupingBy(groupingBy...)
    val byAgeNames = people.groupBy({it.age}, {it.name})
    for (groupByAge in byAgeNames) {
        println("${groupByAge.key} years old:")
        for (name in groupByAge.value) {
            println("  With name ${name}")
        }
    }
}