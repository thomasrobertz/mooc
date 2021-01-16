class Email {

    public String prospectName
    public String emailAddress
    public Date sendDate
    public String notes

    // Use String as type to omit "return" keyword below...
    def display() {

        // split() returns only a string array, can't access the capture group.
        //return this.dump().split(/(\w+)\=/).join("\n")

        // Use reflection and some powerful groovy stream enhancements instead:
        // First filter the instance fields to not be synthetic,
        // then map to a map which can then be formatted in groovy in the collect method.
        // Lastly the resulting string array is joined by newlines.
        return this.class.getDeclaredFields()
            .stream()
            .filter { f -> !f.isSynthetic() }
            .map { f -> [key: f.getName(), value: f.get(this)] }
            .collect { /$it.key = $it.value/ }.join("\n")
    }
}
