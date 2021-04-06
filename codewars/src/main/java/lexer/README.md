I wanted to extend the "ValidBraces" solution and include support for comments.
Normally I would use a parser for this but I wanted to see how easy it would be to extend the solution I built.
After all that is one of the reasons for putting in work upfront to think a bit about design, and create an object oriented solution.

It worked out quite nicely "all" I had to do was to add a lexer with a state changing mechanism for comments. 
(lexer inspired by https://stackoverflow.com/questions/43067869/lexical-analyser-in-java/43067962)
   
