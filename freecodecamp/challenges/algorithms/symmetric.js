function sym(argv) {

  let leftOperand = null; // leftOperand is temporary placeholder
  let solution = []; // solution is just a collector

  // arguments is not an array, destructure
  [...arguments].forEach(operand => {
    // Ensure we have two arrays to operate on
    if(leftOperand === null) {
      leftOperand = operand;
    } 
    else {
      // Reset solution, else we will get doubles
      solution = [];
      // Take from left all not in right
      leftOperand.filter(
        l => !operand.includes(l)
      ).concat(operand.filter(
        // Append all from right not in left
        r => !leftOperand.includes(r)
      )).forEach(element => {
        // Which are not in solution yet
        if(!solution.includes(element)) {
          solution.push(element)
        }
      });
      // Propagate binary operation step result to next iteration, if any
      leftOperand = solution;
    }
  });
  return solution;
}

console.log(sym(
  [3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3]))
