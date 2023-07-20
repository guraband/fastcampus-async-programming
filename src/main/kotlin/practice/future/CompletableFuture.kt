package practice.future

import java.util.concurrent.CompletableFuture

fun sum(n1: Int, n2: Int): Int {
    return n1 + n2
}

fun main() {
    val completableFutire = CompletableFuture.supplyAsync {
        Thread.sleep(2_000)
        sum(100, 200)
    }

    println("계산 시작")
    completableFutire.thenApplyAsync(::println)

    while (!completableFutire.isDone) {
        Thread.sleep(500)
        println("계산 결과를 집계 중입니다.")
    }

    println("계산 종료")
}