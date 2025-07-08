package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import org.example.storage.SongStorage
import org.example.models.Song

fun Route.chartsRoute() {
    // 트렌딩 노래 조회
    get("/charts/trending") {
        val type = call.request.queryParameters["type"] ?: "song"

        when (type) {
            "song" -> {
                val trendingSongs = SongStorage.songs
                    .filter { it.ranking != null }
                    .sortedBy { it.ranking }
                    .take(10)

                call.respond(trendingSongs)
            }

            "playlist" -> {
                call.respond(HttpStatusCode.NotImplemented, "플레이리스트 트렌딩은 별도 구현이 필요합니다.")
            }

            else -> {
                call.respond(HttpStatusCode.BadRequest, "type은 'song' 또는 'playlist'여야 합니다.")
            }
        }
    }
}
