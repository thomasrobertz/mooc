
val survive = Pair(10, 5)
val die = Pair(7, 4)

fun hero(bullets: Int, dragons: Int) : Boolean {
    return bullets >= 2 * dragons
}

require(hero(survive.first, survive.second))
require(!hero(die.first, die.second))