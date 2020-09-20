function insertionSort(array) {

    const length = array.length
    
    const slide = (array, sub, current) => {
        // If the element to the right is bigger than or equal to the current element,
        // slide the elements over to the right one place
        if(array[sub] >= current) {
            array[sub + 1] = array[sub]
            array[sub] = current
        }      
    }
  
    // First walk forwards through the array, start at the second item because 
    // the first item is considered already sorted.
    for(let i = 1; i < length; i++) {
      const current = array[i]
      // On each pass, walk backwards through the solution array and (if necessary,)
      // slide items until the current element fits.
      for(let sub = i - 1; sub >= 0; sub--) {
        slide(array, sub, current)
      }
    }

    return array;
  }