package KotlinSamples.Async

// Needed
//implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"

// A suspend function is a special kind of function that can be paused and resumed at a later time 
// without blocking the thread in which it is running.

/*
1. Non-Blocking Execution
Suspend functions can perform long-running operations without blocking the thread on which they operate. 
This is essential in environments where you want to maintain responsiveness, such as in UI applications where 
blocking the main thread can lead to an unresponsive user interface.
2. Sequential Code for Asynchronous Operations
Even though the operations performed might be asynchronous, the code in suspend functions can be written in a 
sequential and straightforward manner, resembling traditional synchronous code. This makes it easier to read and maintain.
3. Cooperative Multitasking
Suspend functions only suspend their execution at specific suspension points, which are usually at calls to 
other suspend functions. This means they are cooperative; they don't preemptively yield control but rather do so at
predefined points, which helps in efficiently managing tasks.
4. Integration with Coroutines
Suspend functions are the building blocks of coroutines in Kotlin. They can be invoked within coroutine builders like 
launch, async, etc., which are provided by Kotlin’s coroutine library.
*/

//https://play.kotlinlang.org/#eyJ2ZXJzaW9uIjoiMS44LjIxIiwiY29kZSI6ImltcG9ydCBrb3RsaW54LmNvcm91dGluZXMuKlxuXG5zdXNwZW5kIGZ1biBtYWluKCkgPSBjb3JvdXRpbmVTY29wZSB7XG4gICAgbGF1bmNoIHsgXG4gICAgICAgZGVsYXkoMTAwMClcbiAgICAgICBwcmludGxuKFwiS290bGluIENvcm91dGluZXMgV29ybGQhXCIpIFxuICAgIH1cbiAgICBwcmludGxuKFwiSGVsbG9cIilcbn0iLCJwbGF0Zm9ybSI6ImphdmEiLCJhcmdzIjoiIn0=

import kotlinx.coroutines.*

// Define a suspend function
suspend fun performLongRunningTask() {
    delay(1000) // Simulates a long-running task by suspending the function for 1 second
    println("Task completed")
}

fun main() = runBlocking {
    launch {
        performLongRunningTask()
    }
}

// Async / Await
fun main() = runBlocking {
    val deferred: Deferred<Int> = async(Dispatchers.Default) {
        // Some computation or asynchronous operation
        5
    }
    println("Result: ${deferred.await()}")
}


//Channels in Kotlin are similar to BlockingQueues but are designed for coroutines,
//allowing for safe transfer of data between them without needing complex thread synchronization.


import kotlinx.coroutines.channels.*

suspend fun produceData(channel: Channel<Int>) {
    for (x in 1..5) channel.send(x * x)
    channel.close()
}

suspend fun processData(channel: Channel<Int>) {
    for (y in channel) println(y)
}

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch { produceData(channel) }
    launch { processData(channel) }
}

// Kotlin's Flow is analogous to streams in Java and is used for representing asynchronous data streams that can emit
// multiple values sequentially, unlike Deferred, which can only hold a single value.
import kotlinx.coroutines.flow.*

fun produceNumbers(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(100) // Simulate some delay
        emit(i)
    }
}

fun main() = runBlocking {
    produceNumbers().collect { value ->
        println(value)
    }
}