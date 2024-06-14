//https://www.codewars.com/kata/58712dfa5c538b6fc7000569

fun countRedBeads(nBlue: Int): Int = if (nBlue == 0) 0 else (nBlue - 1) * 2

require(0 == countRedBeads(0));
require(0 == countRedBeads(1));
require(2 == countRedBeads(2));
require(4 == countRedBeads(3));
require(6 == countRedBeads(4));
