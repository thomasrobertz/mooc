// Solution to https://www.freecodecamp.org/learn/coding-interview-prep/algorithms/inventory-update

function updateInventory(arr1, arr2) {

    // Create an array of just the product names
    let currentByKeys = arr1.map(i => i[1])
    let newItems = []
    
    if(arr1.length == 0) {
        // Empty current inventory, just copy all items.
        arr1 = arr2
    } else {

        // Bubble through each inventory, if the item exists update the count, otherwise add new.
        arr2.forEach(n => {
            arr1.forEach(c => {
                if (currentByKeys.includes(n[1])) {
                    // Item exists, update the count.
                    if(c[1] == n[1]) {
                        c[0] += n[0]
                    }
                } 
                else {
                    if(!newItems.includes(n)) {
                        // Add new item. Becuase we are bubbling, add only once.
                        newItems.push(n)
                    }
                }
            })
        })
    }

    // Add the new items that were created above, sort by name and return result.
    return arr1.concat(newItems).sort((a, b) => {
        if(a[1] > b[1]) return 1;
        if(a[1] < b[1]) return -1;
        return 0;
    });
}

//console.log(updateInventory(curInv, newInv));

//[[88, "Bowling Ball"], [2, "Dirty Sock"], [3, "Hair Pin"], 
//[3, "Half-Eaten Apple"], [5, "Microphone"], [7, "Toothpaste"]]
//console.log(updateInventory([[21, "Bowling Ball"], [2, "Dirty Sock"], [1, "Hair Pin"], [5, "Microphone"]], 
 //   [[2, "Hair Pin"], [3, "Half-Eaten Apple"], [67, "Bowling Ball"], [7, "Toothpaste"]]))

//[[67, "Bowling Ball"], [2, "Hair Pin"], [3, "Half-Eaten Apple"], [7, "Toothpaste"]]
console.log(updateInventory([], [[2, "Hair Pin"], [3, "Half-Eaten Apple"], [67, "Bowling Ball"], [7, "Toothpaste"]]))
