package springwebflux.coroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun sum(a: Int, b: Int) = a + b

fun main() = runBlocking {
    val result1 = async {
        delay(2000)
        println("result1 processing...")
        sum(1, 3)
    }

    val result2 = async {
        delay(1000)
        println("result2 processing...")
        sum(1, 3)
    }

    val elapsedTime = measureTimeMillis {
        println("1")
        println("result1 : ${result1.await()}")
        println("2")
        println("result2 : ${result2.await()}")
        println("3")
    }
    println("$elapsedTime ms")

    println("11")
    println("result11 : ${result1.await()}")
    println("22")
    println("result22 : ${result2.await()}")
    println("33")
}