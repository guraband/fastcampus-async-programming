package springwebflux.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicInteger

data class Book(val id: Int, val name: String, val price: Int)

@Service
class BookService {

    private final val nextId = AtomicInteger(0)

    val books = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "Head First Java", 30_000),
        Book(id = nextId.incrementAndGet(), name = "혼자 공부하는 자바", 25_000),
    )

    fun getAll(): Flux<Book> {
//        return Flux.fromIterable(books)
        return books.toFlux()
    }

    fun get(id: Int): Mono<Book> {
//        return Mono.justOrEmpty(books.find { it.id == id })
        return books.find { it.id == id }.toMono()
    }

    fun add(request: Map<String, Any>): Mono<Book> {
        return Mono.just(request)
            .map { map ->
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int,
                )

                books.add(book)
                book
            }
    }

    fun delete(id: Int): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }
}
