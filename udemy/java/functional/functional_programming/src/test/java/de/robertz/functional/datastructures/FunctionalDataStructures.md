### Functional Datastructures

Functional Datastructures are (meant to be):
* **Immutable**
  * We do have immutable datastructures in Java, f.i. Collections.unmodifiableSet. 
   The main problem with these is that when we create them, the original has to be copied over (Bad performance) 
* **Persistent**
  * Similar to immutability, but means that when we do need to say, add an element, we return a new datastructure that contains all elements of the original (Preserving, or **persisting** the previous state).
* **Referential Transparency**
  * For some given input, the output will always be the same. Or, we can substitute a function call with it's value.

### Example

A good example of applying such techniques is a functional linked list where we want to add an element at the front:

```
            ┌────────┐      ┌────────┐  
      Front │        │      │        │  
            │   1    ├─────►│   2    │  
            └────────┘      └────────┘
```        

Instead of moving/copying/shifting elements, we simply insert the new element and move the "Front" pointer,
thereby "reusing" the previous persistent state:
                                
```
                                           
            ┌────────┐      ┌────────┐     
            │        │      │        │     
            │   1    ├─────►│   2    │     
            └───▲────┘      └────────┘     
                │                          
                │                          
                │                          
            ┌───┴────┐                     
      Front │        │                     
            │  3     │                     
            └────────┘                     
                                           
```   
This not only preserves the previous data, but is also **fast** and **efficient**.

### Benefits

With these three concepts applied to functional datastructures, besides speed and efficiency, 
we are also thread safe, since data cannot be modified!

Therefore no need for safeguards, sync, locks...
 
### More on Persistence: Ephemeral and Persistent DS

#### Ephemeral Data Structures
Ephemeral data structures are traditional data structures that do not (necessarily) retain their previous versions after updates. When you modify such a structure, the change is in-place, and the previous state is lost. This is common in imperative programming languages where in-place modification is a typical pattern.
#### Persistent Data Structures
Persistent data structures, on the other hand, preserve their previous versions when they are modified. They are designed to be immutable, meaning that instead of altering the original structure, any modification operation returns a new structure that reflects the change, leaving the original one intact. This approach is particularly prevalent in functional programming because it aligns with the principles of immutability and referential transparency.

Persistent data structures are not inherently slower or more memory-consuming than their ephemeral counterparts, thanks to advanced implementation techniques such as structural sharing. This technique allows new versions of the data structure to share parts of their internal structure with previous versions, minimizing the need for duplication and efficient memory usage.