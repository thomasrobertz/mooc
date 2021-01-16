int oppprtunityOne = 1000
int oppprtunityTwo = 4000
int oppprtunityThree = 8000

List opportunities = [oppprtunityOne, oppprtunityTwo, oppprtunityThree].reverse()

int opportunitiesTotal = 0
println "Found ${opportunities.size} opportunities."

while(opportunities) {  // Groovy truthy
    int opportunity = opportunities.pop()
    println opportunity
    opportunitiesTotal += opportunity
    println "The opportunities total is now: ${opportunitiesTotal}"
}
