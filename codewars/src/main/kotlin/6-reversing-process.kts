// https://www.codewars.com/kata/5dad6e5264e25a001918a1fc

// I don't believe this should be 6 kyu.
// I used ChatGPT to help with the modular inverse and decoding.

fun decode(r: String): String {
    val matches = "([0-9]+)(\\w+)".toRegex().find(r)?.groupValues?.drop(1) ?: emptyList()
    val number = matches[0].toInt()
    val content = matches[1]

    return decodeString(content, number)
}

fun decodeString(encoded: String, num: Int): String {
    val inverseNum = modularInverse(num, 26) ?: return "Impossible to decode"

    return encoded.map { char ->
        val y = char - 'a'
        val x = (y * inverseNum) % 26
        (x + 'a'.code).toChar()
    }.joinToString("")
}

fun modularInverse(num: Int, mod: Int): Int? {
    var a = num % mod
    for (x in 1 until mod) {
        if ((a * x) % mod == 1) {
            return x
        }
    }
    return null
}

require(decode("6015ekx") == "mer")
require(decode("1273409kuqhkoynvvknsdwljantzkpnmfgf") == "uogbucwnddunktsjfanzlurnyxmx")
require(decode("1877138eieaqgumigywmicwgcgg") == "Impossible to decode")
