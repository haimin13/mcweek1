package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.storage.GenreStorage

fun Route.genreRoutes() {
    route("/genres") {
        get {
            call.respond(GenreStorage.getAllGenres())
        }
    }
}