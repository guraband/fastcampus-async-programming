package springwebflux.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.concurrent.thread

fun main() = runBlocking {

    repeat(10_000) { i ->
        launch {
            delay(100)
            println(i)
        }
//        thread {
//            Thread.sleep(100)
//            println(i)
//        }
    }
}

fun <T> println(msg: T) {
    kotlin.io.println("${LocalDateTime.now()} [${Thread.currentThread().name}] $msg")
}