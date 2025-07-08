package org.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.routing.friendsRoute
import org.example.routing.profileRoute
import org.example.routing.genreRoute
import org.example.models.Playlist
import org.example.storage.PlaylistStorage
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.http.HttpStatusCode


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

            // 플리 추가
            post("/playlists/add") {
                try {
                    val playlist = call.receive<Playlist>()
                    println("✅ 받은 플리: $playlist")
                    PlaylistStorage.add(playlist)
                    call.respondText("플리 추가 완료")
                } catch (e: Exception) {
                    println("❌ 오류 발생: ${e.message}")
                    call.respond(HttpStatusCode.BadRequest, "역직렬화 오류: ${e.message}")
                }
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

            friendsRoute()
            profileRoute()
            genreRoute()
        }
    }.start(wait = true)
}
