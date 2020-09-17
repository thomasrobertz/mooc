function permAlone(str) {

    const performPermutationAlone = (str) => {

      const length = str.length
      let result = []

      if (length < 2) {
          // The last recusrion has been reached
          // (Or a single character string has been passed)
          return str
      }

      // Compare each character to the one before
      const noRepeats = (input) => {
        let previous = ""
        for (let k = 0; k < input.length; k++) {
          let current = input[k]
          if (current == previous) {
            return false
          }
          previous = current
        }
        return true
      }

      for(let i = 0; i < length; i++) {
    
        // Cut the string into three parts:
        // 1. Pick a single character to be the pivot
        const pivot = str[i]
        // 2. The left part of the string before the pivot
        const left = str.slice(0, i)
        // 3. Thr right part of the string after the pivot
        const right = str.slice(i + 1, length)
    
        // The remaining characters (without the pivot)
        const restructured = left + right 
    
        // Call the function recursively with the remainig characters
        for(let permutation of performPermutationAlone(restructured)) {
          
          // Rebuild the now permutated string
          let rebuild = pivot + permutation
          
          // Filter to only include results with no repetition
          if(noRepeats(rebuild)) {
            result.push(rebuild)
          }
        }
      }
      return result      
   }

   return performPermutationAlone(str).length
}
  
console.log(permAlone('aaabb'));
