// https://www.codewars.com/kata/526c7363236867513f0005ca

fun isLeapYear(year: Int) : Boolean {
    val divBy4 = year.rem(4) == 0
    val divBy100 = year.rem(100) == 0
    val divBy400 = year.rem(400) == 0

    return ((divBy4 && !divBy100) || divBy400)
}

require(isLeapYear(2020) == true)
require(isLeapYear(2000) == true)
require(isLeapYear(2015) == false)
require(isLeapYear(2100) == false)