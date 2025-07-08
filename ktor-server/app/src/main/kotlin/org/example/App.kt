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


fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json { prettyPrint = true })
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



//            // 실제 API
//            route("/playlists") {
//                post("/add") {
//                    val playlist = call.receive<Playlist>()
//                    PlaylistStorage.add(playlist)
//                    call.respondText("플리 추가 완료")
//                }
//
//                get("/list") {
//                    val id = call.request.queryParameters["id"]?.toIntOrNull()
//                    val type = call.request.queryParameters["type"] ?: "all"
//
//                    if (id == null) {
//                        call.respondText("쿼리 파라미터 'id'는 필수입니다.", status = io.ktor.http.HttpStatusCode.BadRequest)
//                        return@get
//                    }
//
//                    val result = PlaylistStorage.getByUser(id, type)
//                    call.respond(result)
//                }
//            }

            // 플리 추가
            post("/playlists/add") {
                val playlist = call.receive<Playlist>()
                PlaylistStorage.add(playlist)
                call.respondText("플리 추가 완료")
            }

            // 플리 조회
            get("/playlists/list") {
                val id = call.request.queryParameters["id"]?.toIntOrNull()
                val type = call.request.queryParameters["type"] ?: "all"

                if (id == null) {
                    call.respondText("쿼리 파라미터 'id'는 필수입니다.", status = io.ktor.http.HttpStatusCode.BadRequest)
                    return@get
                }

                val result = PlaylistStorage.getByUser(id, type)
                call.respond(result)
            }
        }
    }.start(wait = true)
}
