We should never use .block() on a Mono or Flux.
It will block the thread until the result is available.
Which completely defeats the purpose of using reactive programming
and results to pretty much the same as traditional programming.

Then what is .block() for?
-> Experimenting
    Debugging
    Unit tests
    Initialization or similar routines (Check roles and permissons against another service at startup)
    ...
    Anything else where we must block