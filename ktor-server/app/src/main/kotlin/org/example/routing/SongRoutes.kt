package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import org.example.storage.SongStorage
import org.example.models.Song

fun Route.songRoute() {

    // 노래 상세 정보 조회
    get("/songs/{id}") {
        val songId = call.parameters["id"]?.toIntOrNull()
        if (songId == null) {
            call.respond(HttpStatusCode.BadRequest, "노래 ID가 유효하지 않습니다.")
            return@get
        }

        val song = SongStorage.songs.find { it.id == songId }
        if (song == null) {
            call.respond(HttpStatusCode.NotFound, "해당 ID의 노래를 찾을 수 없습니다.")
        } else {
            call.respond(song)
        }
    }
}
