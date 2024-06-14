//https://www.codewars.com/kata/58acfe4ae0201e1708000075

fun inviteMoreWomen(l: List<Int>): Boolean {
    var men = 0
    var women = 0
    l.forEach{
        if(it == 1) {
            men++
        }
        else {
            women++
        }
    }
    return (women < men)
}