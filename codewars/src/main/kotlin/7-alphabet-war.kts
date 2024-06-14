//https://www.codewars.com/kata/59377c53e66267c8f6000027

fun alphabetWar(fight: String): String {
    var leftCount = 0
    var rightCount = 0
    fight.forEach {
        when(it) {
            'w' -> leftCount += 4
            'p' -> leftCount += 3
            'b' -> leftCount += 2
            's' -> leftCount += 1
        }
        when(it) {
            'm' -> rightCount += 4
            'q' -> rightCount += 3
            'd' -> rightCount += 2
            'z' -> rightCount += 1
        }
    }
    if (leftCount > rightCount) {
        return "Left side wins!"
    }
    else if(rightCount > leftCount) {
        return "Right side wins!"
    }
    return "Let's fight again!"
}