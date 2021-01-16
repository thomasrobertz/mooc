println "Welcome to Groovy " + getGroovyVersion()
println "Today's date is " + new Date().toString()

def sum = 3 + 5
println "The sum is " + sum

public String getGroovyVersion() {
    try {
        return org.codehaus.groovy.runtime.InvokerHelper.version
    }
    catch (Throwable ignore) { }
    return GroovySystem.version
}