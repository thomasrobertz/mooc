int oppprtunityOne = 1000
int oppprtunityTwo = 4000
int oppprtunityThree = 8000

List opportunities = [oppprtunityOne, oppprtunityTwo, oppprtunityThree]

int opportunitiesTotal = 0
println "Found ${opportunities.size} opportunities."

for(opportunity in opportunities) {
    opportunitiesTotal += opportunity
    println "Opportuinty: ${opportunity}, Total: ${opportunitiesTotal}"
}