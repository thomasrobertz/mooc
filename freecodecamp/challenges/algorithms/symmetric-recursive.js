function sym(args) {
    destructuredArgs = [...arguments] 
    return performSym(destructuredArgs.shift(), destructuredArgs)
}

function performSym(leftOperand, args) {
    let solution = []
    rightOperand = args.shift()
    leftOperand.filter(
        l => !rightOperand.includes(l)).concat(
            rightOperand.filter(
                r => !leftOperand.includes(r))
    ).forEach(element => {
        if(!solution.includes(element)) {
            solution.push(element)
        }
    })
    if (args.length === 0) {
        return solution
    }
    return performSym(solution, args)
}

//console.log(sym([1, 2, 3], [5, 2, 1, 4]))
//console.log(sym([1, 2, 3, 3], [5, 2, 1, 4]))
//console.log(sym([1, 2, 3], [5, 2, 1, 4, 5]))
//console.log(sym([1, 2, 5], [2, 3, 5], [3, 4, 5]))
//console.log(sym([1, 1, 2, 5], [2, 2, 3, 5], [3, 4, 5, 5]))
//console.log(sym( [3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3]))
console.log(sym([3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3], [5, 3, 9, 8], [1])) 