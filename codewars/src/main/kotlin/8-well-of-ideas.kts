// https://www.codewars.com/kata/57f222ce69e09c3630000212

fun well(x: Array<String>): String {
    val r:Int = x.filter { it == "good" }.count() // could have use count({...})
    return when {
        r in 1..2 -> "Publish!"
        r > 2 -> "I smell a series!"
        else -> "Fail!"
    }
}

require("Publish!" == well(arrayOf("good", "bad", "bad")))