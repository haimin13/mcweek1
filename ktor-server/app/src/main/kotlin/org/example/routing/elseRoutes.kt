package org.example.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.storage.*

fun Route.friendsLogRoute() {
    // 친구 활동 로그 조회
    get("/friendsupdate/{id}") {
        try {
            val userId = call.parameters["id"]?.toIntOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "유저 ID가 유효하지 않습니다.")
                return@get
            }

            val user = UserStorage.users.find { it.id == userId }
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "유저를 찾을 수 없습니다.")
                return@get
            }

            val friendIds = user.friends ?: emptyList()
            val friendLogs = UserLikeLogStorage.logs.filter { friendIds.contains(it.userId) }
            call.respond(friendLogs)
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, "서버 오류 발생: ${e.message}")
        }
    }
}

fun Route.genreRoute() {
    // 모든 장르 목록 조회
    get("/genres") {
        call.respond(GenreStorage.genres)
    }
}

fun Route.artistRoute() {
    // 아티스트 상세 조회
    get("/artists/{id}") {
        val artistId = call.parameters["id"]?.toIntOrNull()
        if (artistId == null) {
            call.respond(HttpStatusCode.BadRequest, "아티스트 ID가 유효하지 않습니다.")
            return@get
        }

        val artist = ArtistStorage.artists.find { it.id == artistId }
        if (artist == null) {
            call.respond(HttpStatusCode.NotFound, "해당 ID의 아티스트를 찾을 수 없습니다.")
        } else {
            call.respond(artist)
        }
    }
}
