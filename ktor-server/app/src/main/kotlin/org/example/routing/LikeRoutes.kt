package org.example.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.models.UserLikeLog
import org.example.storage.*
import java.time.LocalDateTime

fun Route.likesRoute() {

    // 좋아요 추가
    post("/likes/add") {
        val userId = call.request.queryParameters["userId"]?.toIntOrNull()
        val itemType = call.request.queryParameters["itemType"]
        val itemId = call.request.queryParameters["itemId"]?.toIntOrNull()

        if (userId == null || itemType == null || itemId == null) {
            call.respond(HttpStatusCode.BadRequest, "userId, itemType, itemId는 필수입니다.")
            return@post
        }

        val user = UserStorage.users.find { it.id == userId }
        if (user == null) {
            call.respond(HttpStatusCode.NotFound, "유저를 찾을 수 없습니다.")
            return@post
        }

        // 로그에 중복 확인 후 추가
        val alreadyLiked = UserLikeLogStorage.logs.any {
            it.userId == userId && it.itemType == itemType && it.itemId == itemId
        }
        if (!alreadyLiked) {
            UserLikeLogStorage.logs.add(UserLikeLog(userId, itemType, itemId, LocalDateTime.now()))
        }

        when (itemType) {
            "song" -> {
                SongStorage.songs.find { it.id == itemId }?.likedBy?.addIfAbsent(userId)
                user.likedSongs?.addIfAbsent(itemId)
            }
            "playlist" -> {
                PlaylistStorage.playlists.find { it.id == itemId }?.likedBy?.addIfAbsent(userId)
                user.likedPlaylists?.addIfAbsent(itemId)
            }
            "artist" -> {
                ArtistStorage.artists.find { it.id == itemId }?.likedGenres?.addIfAbsent(userId) // 구조 맞게 수정 가능
                user.likedArtists?.addIfAbsent(itemId)
            }
            else -> {
                call.respond(HttpStatusCode.BadRequest, "itemType은 'song', 'playlist', 'artist' 중 하나여야 합니다.")
                return@post
            }
        }

        call.respondText("좋아요 완료!")
    }

    // 좋아요 취소
    delete("/likes/remove") {
        val userId = call.request.queryParameters["userId"]?.toIntOrNull()
        val itemType = call.request.queryParameters["itemType"]
        val itemId = call.request.queryParameters["itemId"]?.toIntOrNull()

        if (userId == null || itemType == null || itemId == null) {
            call.respond(HttpStatusCode.BadRequest, "userId, itemType, itemId는 필수입니다.")
            return@delete
        }

        val user = UserStorage.users.find { it.id == userId }
        if (user == null) {
            call.respond(HttpStatusCode.NotFound, "유저를 찾을 수 없습니다.")
            return@delete
        }

        // 로그 제거
        UserLikeLogStorage.logs.removeIf {
            it.userId == userId && it.itemType == itemType && it.itemId == itemId
        }

        when (itemType) {
            "song" -> {
                SongStorage.songs.find { it.id == itemId }?.likedBy?.remove(userId)
                user.likedSongs?.remove(itemId)
            }
            "playlist" -> {
                PlaylistStorage.playlists.find { it.id == itemId }?.likedBy?.remove(userId)
                user.likedPlaylists?.remove(itemId)
            }
            "artist" -> {
                ArtistStorage.artists.find { it.id == itemId }?.likedGenres?.remove(userId)
                user.likedArtists?.remove(itemId)
            }
            else -> {
                call.respond(HttpStatusCode.BadRequest, "itemType은 'song', 'playlist', 'artist' 중 하나여야 합니다.")
                return@delete
            }
        }

        call.respondText("좋아요 취소 완료!")
    }
}

fun <T> MutableList<T>?.addIfAbsent(item: T) {
  if (this != null && !this.contains(item)) this.add(item)
}