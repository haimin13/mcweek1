package org.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.models.Playlist
import org.example.storage.PlaylistStorage
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.time.LocalDateTime
import io.ktor.server.http.content.*
import io.ktor.http.HttpStatusCode
import org.example.routing.playlistRoute
import org.example.routing.songRoute
import org.example.routing.chartsRoute


fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            })
        }

        routing {
            static("/swagger") {
                resources("swagger")
                defaultResource("index.html", "swagger")
            }
            static("/") {
                resources("static") // 여기 openapi.json이 있어야 함
            }

            get("/") {
                call.respondText("서버 정상 작동 중!")
            }

            playlistRoute()
            songRoute()
            chartsRoute()
            

        }
    }.start(wait = true)
}
