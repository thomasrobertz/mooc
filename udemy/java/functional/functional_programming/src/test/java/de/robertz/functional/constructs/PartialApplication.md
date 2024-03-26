Although currying works in conjunction with partial application, it is important to note a subtle difference:

Partial Application is slightly different from currying. Partial application refers to the process of fixing a number of arguments to a function, producing another function of smaller arity. It's a technique that can be applied to both curried and uncurried functions.

`f(x, y, z)` becomes `f(x)(y, z)` or `f(x, y)(z)`

Compare with currying:

`f(x, y, z)` becomes `f(x)(y)(z)`

Though in P.A. we can also choose different functions to apply after the original function.
In currying this is not possible.