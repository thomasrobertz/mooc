### Collector signature

Collectors can have the following arguments:

```
Supplier<A> supplier

BiConsumer<A, T> accumulator

BinaryOperator<A> combiner

Function<A, R> finisher

Set<Characteristics> characteristics
```

### Collector example

It is common to have overlaods that don't accept a finisher, since it may only be needed in special cases.
<br />
We can see these arguments looking at the Collectors toList() implementation:

```
    public static <T> Collector<T, ?, List<T>> toList() {
        return new CollectorImpl<>(
            ArrayList::new, 
            List::add,
            (left, right) -> { 
                left.addAll(right); return left; 
            },
            CH_ID);
    }
```
Firstly, we see that it returns a CollectorImpl. Interesting to note that while it plays a fundamental role in defining how elements are collected from streams, it is not part of the public API.
(Preventing breaking changes between future Java versions, and allowing developers to focus on the "what" rather than the "how." )

The supplier is a new ArrayList, which is filled (accumulated) one by one with List::add.
This constitutes the basic functionality. 
The combiner is used in parallel applications, where it in this case, just adds all elements of the second (right) ArrayList to the first (left).
The finisher is not used here, indicating that the accumulation process itself is sufficient for constructing the final result.

### Example Collector usage
Compare to the gist of ReferencePipeline's collect() method:

```
    public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
        A container;
        
        // ...
        
            container = collector.supplier().get();
            BiConsumer<A, ? super P_OUT> accumulator = collector.accumulator();
            forEach(u -> accumulator.accept(container, u));
        
        // ...
        
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
               ? (R) container
               : collector.finisher().apply(container);  
    }
    
    // ...
    
    @Override
    public void forEach(Consumer<? super P_OUT> action) {
        // evaluate applies a terminal operation to the pipeline.
        // makeRef constructs a terminal operation that performs an action for every element of a stream.
        evaluate(ForEachOps.makeRef(action, false));
    }          
```

We can see how the container and accumulator are set from the Collector being passed in.
Then the accumulator accepts all the elements into the container.
This accumulating function is passed down to perform terminal actions on the stream.
Next, depending on the characteristics, the container is returned with or without applying a finisher.


### Finisher

Can be used to further process the results of accumulator / combiner.
It is a function that performs the final transformation on the collected data before it is returned from the collector.

A typical use case might be to convert the return value into an unmodifialbe one:

```
Collector<String, List<String>, List<String>> collector = Collector.of(
    ArrayList::new,          // Supplier
    List::add,               // Accumulator
    (left, right) -> { left.addAll(right); return left; }, // Combiner
    Collections::unmodifiableList);   // Finisher
```

### Characteristics

Describe certain aspects of a collector's behavior. These characteristics inform the framework about how the collector can be expected to function, which can influence performance optimizations and the correctness of the collection process

CONCURRENT: This characteristic indicates that the same result container can be used concurrently by multiple threads without the need for external synchronization. A collector with this characteristic can accumulate elements into a single container from multiple threads simultaneously, making it suitable for parallel stream processing. However, it should only be used when the data source is unordered or when the collected result does not depend on the order of elements.

UNORDERED: This characteristic signifies that the order of elements in the data source does not affect the result of the collection process. Collectors with this characteristic can freely ignore the encounter order of elements, potentially offering performance optimizations, especially in parallel processing. This is useful for operations where the order is not important, such as collecting elements into a set.

IDENTITY_FINISH: This characteristic means that the finisher function is the identity function, which does nothing and simply returns its input. Collectors with this characteristic do not need to perform a final transformation on the result container. It indicates that the accumulator's result is directly the final result of the collector. This is common in collectors that do not require a transformation, such as toList() or toSet(), where the collected container itself is the desired result.

#### When to Use Which:

CONCURRENT: Use this when you are performing a parallel stream operation, and the collector can accumulate results concurrently in a thread-safe manner, without needing the elements to be processed in order. It's not suitable for collectors that depend on the encounter order of elements.

UNORDERED: Opt for this characteristic when the order of elements does not matter for the collection process. This can allow for performance optimizations, especially in parallel processing, as the framework can ignore element ordering.

IDENTITY_FINISH: Choose this when the accumulator's result container does not need any further transformation to become the final result. It simplifies the collector by indicating that the finisher is a no-operation, which can slightly improve performance by avoiding unnecessary function calls.