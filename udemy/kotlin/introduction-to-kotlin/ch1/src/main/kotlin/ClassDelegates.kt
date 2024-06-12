package KotlinSamples.ClassDelegates

fun main(a:Array<String>) {
    val mfd = MultiDevice()
    mfd.print()
    mfd.scan()
}

// We can provide implementations in the primary cons, making the class definition concise
class MultiDevice(printer: Printer = InkPrinter(),
                  scanner: Scanner = OfficeScanner())
    : Printer by printer, Scanner by scanner

interface Printer {
    fun print()
}

interface Scanner {
    fun scan()
}

class InkPrinter : Printer {
    override fun print() {
        println("Printing...")
    }
}

class OfficeScanner : Scanner {
    override fun scan() {
        println("Scanning...")
    }
}