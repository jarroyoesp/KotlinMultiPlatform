@file:UseExperimental(KtorExperimentalLocationsAPI::class)

package org.example.kotlin.multiplatform.backend

import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
//import org.example.kotlin.multiplatform.api.Api

@Location("/{trail...}")
data class IndexPage(val trail: List<String>?)

//@Location(Api.path)
class Api {
    //@Location(Api.V1.path)
    class V1 {
        //@Location("${Api.V1.Paths.greeting}/{who?}")
        data class HelloEndpoint(val who: String?)
    }
}
