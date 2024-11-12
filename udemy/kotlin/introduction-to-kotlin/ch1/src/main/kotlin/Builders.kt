package KotlinSamples.Builders

fun main() {

    val myMenu = menu {
        item("View Profile") {
            println("Profile viewed.")
        }
        item("Edit Profile") {
            println("Profile editor launched.")
        }
        item("Sign Out") {
            println("Signed out.")
        }
    }

    myMenu.show()
    myMenu.select(2)
}

// 'Menu.() ->' is a parameterless lambda invocation on the receiver Menu"
fun menu(init: Menu.() -> Unit): Menu {
    val menu = Menu()
    menu.init()
    return menu
}


class Menu {
    private val items = mutableListOf<MenuItem>()

    fun item(name: String, action: () -> Unit) {
        items.add(MenuItem(name, action))
    }

    fun show() {
        items.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name}")
        }
    }

    fun select(itemNumber: Int) {
        items.getOrNull(itemNumber - 1)?.action?.invoke()
    }
}

data class MenuItem(val name: String, val action: () -> Unit)

