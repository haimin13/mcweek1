package org.example.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.storage.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import org.example.models.ChartResponse

fun Route.chartsRoute() {
    
    //친구들이 좋아요한 콘텐츠 기반 순위 (전체 기간)
    get("/charts/friendsfavorite") {
        try {
            val userId = call.request.queryParameters["id"]?.toIntOrNull()
            if (userId == null) {
                call.respond(HttpStatusCode.BadRequest, "id는 필수입니다.")
                return@get
            }

            val friendIds = UserStorage.users.find { it.id == userId }?.friends ?: emptyList()
            val friendLogs = UserLikeLogStorage.logs.filter { it.userId in friendIds }

            val songLikeCounts = mutableMapOf<Int, Int>()
            val playlistLikeCounts = mutableMapOf<Int, Int>()

            friendLogs.forEach { log ->
                when (log.itemType) {
                    "song" -> songLikeCounts[log.itemId] = songLikeCounts.getOrDefault(log.itemId, 0) + 1
                    "playlist" -> playlistLikeCounts[log.itemId] = playlistLikeCounts.getOrDefault(log.itemId, 0) + 1
                }
            }

            val topSongs = songLikeCounts.entries.sortedByDescending { it.value }
                .mapNotNull { (id, _) -> SongStorage.songs.find { it.id == id } }
            val topPlaylists = playlistLikeCounts.entries.sortedByDescending { it.value }
                .mapNotNull { (id, _) -> PlaylistStorage.playlists.find { it.id == id } }

            call.respond(ChartResponse(topSongs, topPlaylists))
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, "서버 오류 발생: ${e.message}")
        }
    }

    //최근 3일 내 친구들이 좋아요한 콘텐츠 순위
    get("/charts/trendingnow") {
        val userId = call.request.queryParameters["id"]?.toIntOrNull()
        if (userId == null) {
            call.respond(HttpStatusCode.BadRequest, "id는 필수입니다.")
            return@get
        }

        val friendIds = UserStorage.users.find { it.id == userId }?.friends ?: emptyList()
        val now = LocalDateTime.now()
        val logs = UserLikeLogStorage.logs.filter {
            it.userId in friendIds && ChronoUnit.DAYS.between(it.likedTime, now) <= 3
        }

        val songLikeCounts = mutableMapOf<Int, Int>()
        val playlistLikeCounts = mutableMapOf<Int, Int>()

        logs.forEach { log ->
            when (log.itemType) {
                "song" -> songLikeCounts[log.itemId] = songLikeCounts.getOrDefault(log.itemId, 0) + 1
                "playlist" -> playlistLikeCounts[log.itemId] = playlistLikeCounts.getOrDefault(log.itemId, 0) + 1
            }
        }

        val topSongs = songLikeCounts.entries.sortedByDescending { it.value }
            .mapNotNull { (id, _) -> SongStorage.songs.find { it.id == id } }
        val topPlaylists = playlistLikeCounts.entries.sortedByDescending { it.value }
            .mapNotNull { (id, _) -> PlaylistStorage.playlists.find { it.id == id } }

        call.respond(ChartResponse(topSongs, topPlaylists))
    }
}
