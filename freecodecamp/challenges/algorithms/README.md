My solution for the symmetric difference challenge at

https://www.freecodecamp.org/learn/coding-interview-prep/algorithms/find-the-symmetric-difference

I also did a recursive solution, I think the result is a little cleaner.

## Odd occurence count

When I looked at the test

[1, 2, 5], [2, 3, 5], [3, 4, 5]

I noticed that 5 is in the result, even though it is in all arrays.
At first this seemed counter-intuitive.
But, the reason is that each array is evaluated in a sqeuenced binary operation.
So it is like the element gets alternately included / excluded depending on how many times it occurs.
This brought me to another solution:	

Count the distinct elements in all arrays, and the solution should include all elements with odd occurance counts.

So in the test above the counts are:

1:1
2:2
3:2
4:1
5:3

And therefore the solution is 1,4,5.
Note that this is not a binary operation anymore!

I'm not sure whether this really works but it does on the tests from the freecodecamp task.