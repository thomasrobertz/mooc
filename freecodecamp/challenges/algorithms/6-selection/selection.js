function selectionSort(array) {

    const length = array.length
  
    const indexOfMinimumAt = (start) => {
      let minimum = array[start]
      let minimumIndex = start
      for(let i = start + 1; i < length; i++) {
         if (array[i] < minimum) {
           minimum = array[i]
           minimumIndex = i
         }
      }
      return minimumIndex
    } 
  
    for(let i = 0; i < length; i++) {
      const minimumIndex = indexOfMinimumAt(i)
      // No need to check same indexes
      if (minimumIndex > i) {
        // Swap
        const temp = array[i]
        array[i] = array[minimumIndex]
        array[minimumIndex] = temp
      }
    }
  
    return array;
}
  
console.log(
  selectionSort([1, 4, 2, 8, 345, 123, 43, 32, 5643, 63, 123, 43, 2, 55, 1, 234, 92])
); 