package springwebflux

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

data class User(
    val id: Long,
    val name: String,
)

@Component
class UserHandler {
    val users = listOf(
        User(1, "바트"),
        User(2, "리사"),
    )

    fun getUser(request: ServerRequest): Mono<ServerResponse> {
        return users.find {
            it.id == request.pathVariable("id").toLong()
        }?.let {
            ServerResponse.ok().bodyValue(it)
        } ?: ServerResponse.notFound().build()
    }

    fun getAllUsers(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue(users);
    }
}
