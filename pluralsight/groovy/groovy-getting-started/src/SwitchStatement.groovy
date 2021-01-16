//def myOpportunity = 6000
// Even in a dynamic language it is _good practice_ to declare the type
// if known and is not expected to change.
// This way also, the compiler is able to identify type errors at compile-time
int myOpportunity = 6000

switch (myOpportunity) {
    case 0..999:
        println("Email")
    case 1001..5000:
        println("Phone")
    default:
    // Cannot use something like "case >5000:"
        println("Face to face")
}
