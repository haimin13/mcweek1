package org.example.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.data.PlaylistRepository

fun Route.playlistRoutes() {
    route("/playlists") {
        get("/recent") {
            call.respondText("Hello from playlists!")
        }
    }
}
