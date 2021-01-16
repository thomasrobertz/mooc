
int oppprtunityOne = 1000
int oppprtunityTwo = 4000
int oppprtunityThree = 8000

List opportunities = [oppprtunityOne, oppprtunityTwo, oppprtunityThree]

int opportunitiesTotal = 0
println "Found ${opportunities.size} opportunities."

for(int i = 0; i < opportunities.size; i++) {
    opportunitiesTotal += opportunities[i]
    println "Opportuinty: ${opportunities[i]}, Total: ${opportunitiesTotal}"
}