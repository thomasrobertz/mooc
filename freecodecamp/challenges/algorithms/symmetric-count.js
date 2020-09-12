function sym(args) {
    // Dictionary to hold encountered elements with their counts
    let occurance = {};
    [...arguments].forEach(a => {
        let cleaned = [];
        // Remove double entries
        a.forEach(e => { if(!cleaned.includes(e)) {
            cleaned.push(e);
        }})
        // Count each entry and put in dictionary
        cleaned.forEach(e => {
            if(occurance[e]) {
                occurance[e]++;
            }
            else {
                occurance[e] = 1;
            }
        })
    }) 
    // Filter by odd count and map keys to integers
    return Object.keys(Object.fromEntries(Object.entries(occurance).filter(
        ([k, v]) => v % 2 == 1))).map(k => Number(k));
}

//console.log(sym([1, 2, 3], [5, 2, 1, 4])) // 3 4 5
//console.log(sym([1, 2, 3, 3], [5, 2, 1, 4]))
//console.log(sym([1, 2, 3], [5, 2, 1, 4, 5]))
//console.log(sym([1, 2, 5], [2, 3, 5], [3, 4, 5])) // 1 4 5
//console.log(sym([1, 1, 2, 5], [2, 2, 3, 5], [3, 4, 5, 5]))
//console.log(sym( [3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3])) // [2, 3, 4, 6, 7]
console.log(sym([3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3], [5, 3, 9, 8], [1])) // [1, 2, 4, 5, 6, 7, 8, 9]