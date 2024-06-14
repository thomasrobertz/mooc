// https://www.codewars.com/kata/57fb09ef2b5314a8a90001ed

fun replaceExl(x: String): String {
    return x.replace("[aeiouAEIOU]".toRegex(), "!")
}

replaceExl("i!")