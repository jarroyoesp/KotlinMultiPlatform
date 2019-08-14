package org.example.kotlin.multiplatform.backend

import io.ktor.http.ContentType
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.routing.Route
import io.ktor.routing.accept

@UseExperimental(KtorExperimentalLocationsAPI::class)
fun Route.index() {
    static("frontend") {
        resource("JsApp.bundle.js")
    }

    accept(ContentType.Text.Html) {
        defaultResource("index.html")
    }
}
