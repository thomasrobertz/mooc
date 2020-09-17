function pairwise(arr, arg) {
    
    const length = arr.length
    let sum = 0
    let accepted = [] // Holds indices of solution pairs

    // Bubble
    for(let i = 0; i < length; i++) {
        for(let j = 0; j < length; j++) {
          // Check whether each index has already been added.
          // Since we are moving from the smallest to the largest index,
          // the requirement of using the smallest possible index should be met.
          if((!accepted.includes(i)) && 
            (!accepted.includes(j))) {
            // We look forward only because results of 
            // the combinations are the same (5+2=2+5)
            if(j > i) {
              if(arr[i] + arr[j] === arg) {
                // Add indices to accepted solutions
                accepted.push(i)
                accepted.push(j)
                sum += i + j
              }
            }
          }
        }
      
    }
    return sum;
}
  
//console.log(pairwise([1,4,2,3,0,5], 7)) // -> 11
console.log(pairwise([], 100)) // 1
  