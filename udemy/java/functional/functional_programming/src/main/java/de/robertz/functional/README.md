Hands-On and Experiments from the course https://adessose.udemy.com/course/functional-programming-and-reactive-programming-in-java 

---

A lot of the constructs and patterns use the same internal pattern to achieve functionality.
Basically, it's just `apply`, used in different ways and constellations.
F.i.: <br />

Chainable Function: `next.apply(current.apply(input))`
<br />
Currying `f2.apply(input); f1.apply(f2)`
<br />
Iterator: `f.apply(element)`
<br />
Decorator: `ingredients.apply(new Burger(...`
<br />
And so on.
<br />
<br />
The flexibility then comes in the caller. Where we see lambdas being passed to the above `apply()` methods as in:
<br />
`ApplyOver.apply(e, i -> i * 2)`
<br />
<br />
This often works in conjunction with several other FP principles, for instance staying immutable:
<br />
`return new Order(cart, targetDestination)`

`return new Burger(burger)`
