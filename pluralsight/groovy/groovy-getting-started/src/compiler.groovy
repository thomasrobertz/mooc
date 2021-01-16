// Running "groovy compile.groovy" executes the file and does not produce a .class file
//
// Running "groovyc compile.groovy" produces a .class file,
// but then we still have to use "groovy compile.groovy"
//
// Groovy is a dynamically compiled language. Compiled but stilly dynamic (Also in the
// sense of interpreted - also see the Groovy REPL groovysh, which is interpreted)

println "Let's get started"

// Some math
println 7%3
println 2**3

// Some string operations
println "1" + "3"
println "1" + 3 // groovy RT dynamics

// Variables

