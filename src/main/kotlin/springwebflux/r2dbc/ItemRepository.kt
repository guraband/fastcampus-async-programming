package springwebflux.r2dbc

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ItemRepository : ReactiveCrudRepository<Item, Long> {
    fun findByName(name: String): Mono<Item>
}