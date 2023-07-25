package springwebflux.coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    launch {
        delay(500)
        println("World!")
    }

    val job1 = launch {
        val elapsedTime = measureTimeMillis {
            delay(150)
        }
        println("aync task-1 $elapsedTime ms")
    }

    val job2 = launch(start = CoroutineStart.LAZY) {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("aync task-2 $elapsedTime ms")
    }

    job1.cancel()
    job2.start()

    println("Hello ")
}