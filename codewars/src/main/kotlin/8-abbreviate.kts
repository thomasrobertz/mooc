//https://www.codewars.com/kata/57eadb7ecd143f4c9c0000a3

val input1 = "P Favuzzi"
val input2 = "Evan Cole"
val input3 = "george clooney"

fun abbrevName(name: String): String {
    return name.split(" ").reduce{i, a -> "${i.first().uppercaseChar()}.${a.first().uppercaseChar()}"}
}

println(abbrevName(input1))
println(abbrevName(input2))
println(abbrevName(input3))