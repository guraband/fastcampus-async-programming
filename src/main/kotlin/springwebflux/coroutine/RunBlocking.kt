package springwebflux.coroutine

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Hello ")
        println(Thread.currentThread().name)
    }

    println("World!")
    println(Thread.currentThread().name)

    doLaunch()
}

fun doLaunch() = runBlocking<Unit> {
    launch {
        println("launch in doLaunch")
        println(Thread.currentThread().name)
    }

    launch {
        println("launch in doLaunch2")
        println(Thread.currentThread().name)
    }

    println("doLaunch")
    println(Thread.currentThread().name)
}