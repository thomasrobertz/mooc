Currying is the process of transforming a function that takes multiple arguments into a sequence of functions that each take a single argument.
Partial application is a technique of fixing a number of arguments to a function, producing another function of smaller arity (number of arguments).

> `f(a, b)` becomes `f(a)(b)`

Currying can also be viewed as a form of decomposition, particularly in the context of functional programming.
It decomposes a function that takes multiple arguments into a series of functions, each taking a single argument.

This decomposition has several practical benefits:

* **Simplification** of Function Calls: By breaking down a function that takes multiple arguments
into a series of functions each taking one argument, currying simplifies the process of function invocation.
It allows for a more modular approach to applying function arguments, which can be particularly useful in scenarios where not all arguments are known or available at the same point in time.
* **Reusability** and Partial Application: Currying naturally leads to partial application, where a function of multiple arguments is transformed into a function that, when given fewer arguments, returns a new function waiting for the rest of the arguments. This is a powerful concept for creating more specific functions from general ones and promotes code reusability.
* **Functional Composition**: Curried functions are easier to compose into new functions. Functional composition is a core concept in functional programming, where you build complex operations out of simpler functions. Currying facilitates this by ensuring functions have a consistent and simple interface (taking one argument), making them more composable.

Higher-order Functions: Currying transforms multi-argument functions into a chain of higher-order functions, each returning another function. This aligns well with the functional programming paradigm, which emphasizes the use of functions as first-class citizens that can be passed around and manipulated like any other data.
