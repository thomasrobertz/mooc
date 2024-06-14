//https://www.codewars.com/kata/56747fd5cb988479af000028

fun getMiddle(word : String) : String {
    var middle = 0
    var start = 0
    if(word.length.rem(2) == 0) {
        middle = word.length / 2
        start = middle - 1
    }
    else {
        middle = (word.length + 1) / 2 - 1
        start = middle
    }
    return word.substring(start..middle)
}

println(getMiddle("A"))