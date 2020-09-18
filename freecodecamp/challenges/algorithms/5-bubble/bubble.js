function bubbleSort(array) {
 
    const length = array.length
  
    // The outer, "slow" bubble
    for(let o = 0; o < length; o++) {
      // The inner, "fast" bubble
      for(let i = 0; i < length; i++) {
        // We don't need to compare same elements at the same index
        if(o !== i) {
  
          // If the outer element is bigger than the current element...
          const isBigger = array[o] > array[i]
          
          // ...but comes before the current element...
          const comesBefore = o < i
          
          if(isBigger && comesBefore) {
            // ...then swap the two elements:
            const temp = array[i]
            array[i] = array[o]
            array[o] = temp
          }
        }
      }
    }
    return array
  }
  
  //console.log(
  //  bubbleSort([1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92]));
  //console.log(bubbleSort([4, 1,88, 97,1,9, 3]))
  console.log(bubbleSort([4, 1, 9, 3]))