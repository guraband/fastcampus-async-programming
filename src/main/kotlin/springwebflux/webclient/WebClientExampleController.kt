package springwebflux.webclient

import kotlinx.coroutines.reactive.awaitFirst
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class WebClientExampleController {
    val host = "http://localhost:8080"

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/webclient-example/block/sleep")
    fun getBooksBlockingWay(): String {
        val d = System.currentTimeMillis()
        log.info("Start RestTemplate")

        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(
            "$host/sleep/1", HttpMethod.GET, null,
            object : ParameterizedTypeReference<String>() {}
        )

        val response2 = restTemplate.exchange(
            "$host/sleep/2", HttpMethod.GET, null,
            object : ParameterizedTypeReference<String>() {}
        )

        val response3 = restTemplate.exchange(
            "$host/sleep/3", HttpMethod.GET, null,
            object : ParameterizedTypeReference<String>() {}
        )

        val result = response.body!!

        log.info("result : {}", result)
        log.info("Finish RestTemplate, {}", (System.currentTimeMillis() - d).toFloat() / 1_000)

        return response.body!!
    }

    @GetMapping("/webclient-example/non-block/sleep")
    fun getBooksNonBlockingWay(): Mono<String> {
        val d = System.currentTimeMillis()
        log.info("Start WebClient")

        val mono1 = webClientCall(1)
        val mono2 = webClientCall(2)
        val mono3 = webClientCall(3)

        log.info("Finish WebClient, {}", (System.currentTimeMillis() - d).toFloat() / 1_000)

        return Mono.zip(mono1, mono2, mono3)
            .map { tuple ->
                tuple.t1 + tuple.t2 + tuple.t3
            }
    }

    // suspend + await 사용시 blocking 방식으로 작동함
    @GetMapping("/webclient-example/non-block2/sleep")
    suspend fun getBooksNonBlockingWay2(): String {
        val d = System.currentTimeMillis()
        log.info("Start WebClient")

        val result1 = webClientCall(1).awaitFirst()
        val result2 = webClientCall(2).awaitFirst()
        val result3 = webClientCall(3).awaitFirst()

        log.info("Finish WebClient, {}", (System.currentTimeMillis() - d).toFloat() / 1_000)

        return result1 + result2 + result3
    }

    private fun webClientCall(seconds: Int): Mono<String> {
        return WebClient.create()
            .get()
            .uri("$host/sleep/$seconds")
            .retrieve()
            .bodyToMono(String::class.java)
            .map {
                log.info("result : {}", it)
                it
            }
    }
}