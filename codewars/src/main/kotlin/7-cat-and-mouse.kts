//https://www.codewars.com/kata/57ee24e17b45eff6d6000164

fun catMouse(s: String): String {
    return if ("\\.+".toRegex().find(s)?.value?.length ?:0 <= 3) "Caught!" else "Escaped!";
}

catMouse("C...m")
catMouse("C..m")
