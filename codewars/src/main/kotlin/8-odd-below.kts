// https://www.codewars.com/kata/59342039eb450e39970000a6

fun oddCount(n: Int): Int {
    return (n - if(n % 2 == 0) 0 else 1) / 2
}

println(oddCount(15))
require(7 == oddCount(15))
require(7511 == oddCount(15023))