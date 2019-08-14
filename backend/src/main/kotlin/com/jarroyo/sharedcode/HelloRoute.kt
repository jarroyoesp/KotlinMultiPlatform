package org.example.kotlin.multiplatform.backend

import io.ktor.http.ContentType
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.routing.Route
import io.ktor.routing.accept
@UseExperimental(KtorExperimentalLocationsAPI::class)
fun Route.hello() {

    //JsonSerializableConverter.register(HelloResponse.serializer())

    accept(ContentType.Application.Json) {
        get<Api.V1.HelloEndpoint> { (who) ->
            val message = if (who == null) "Unknown entity" else "Nice to meet you $who"
            //call.respond(HelloResponse(Greeting(message = message)))
        }
    }
}
