import java.lang.reflect.Field
import java.lang.reflect.Modifier

class Email {

    public String prospectName
    public String emailAddress
    public Date sendDate
    public String notes

    // Use String as type to omit "return" keyword below...
    def display() {

        // split() returns only a string array, can't access the capture group.
        //return this.dump().split(/(\w+)\=/).join("\n")

        // Use reflection instead
        String result = ""
        this.class.getDeclaredFields().each {
            if (!it.isSynthetic()) { // TODO maybe use filter for synthetic, and collect to StringBuilder
                result += it.getName() + ": " + it.get(this) + "\n"
            }
        }
        return result
    }
}
