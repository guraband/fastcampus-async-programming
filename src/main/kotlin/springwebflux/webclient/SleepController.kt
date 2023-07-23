package springwebflux.webclient

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SleepController {
    val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/sleep/{seconds}")
    fun sleep(@PathVariable seconds: Int): String {
        log.info("- Thread will sleep for {} seconds", seconds)

        Thread.sleep(seconds * 1_000L)

        return "Thread was asleep for $seconds seconds."
    }
}