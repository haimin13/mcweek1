package org.example.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode
import org.example.models.Profile
import org.example.storage.UserStorage
import org.example.storage.SongStorage
import org.example.storage.ArtistStorage
import org.example.storage.PlaylistStorage

fun Route.profileRoute() {
    route("/profile") {
        get("/{id}") {
            println("Received request for /profile/${call.parameters["id"]}") // 요청 도달 확인
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
                    return@get
                }

                val user = UserStorage.findUserById(id)
                if (user == null) {
                    call.respondText("User not found", status = HttpStatusCode.NotFound)
                    return@get
                }

                val likedSongs = user.likedSongs.mapNotNull { songId ->
                    SongStorage.findSongById(songId)
                }

                val likedArtists = user.likedArtists.mapNotNull { artistId ->
                    ArtistStorage.findArtistById(artistId)
                }

                val createdPlaylists = user.createdPlaylists.mapNotNull { playlistId ->
                    PlaylistStorage.findPlaylistById(playlistId)
                }

                // return Profile
                val profile = Profile(
                    userId = user.id,
                    nickname = user.nickname,
                    thumbnailId = user.thumbnailId,
                    likedGenres = user.likedGenres,
                    likedSongs = likedSongs,
                    likedArtists = likedArtists,
                    createdPlaylists = createdPlaylists
                )

                call.respond(profile)
            } catch (e: Exception) {
                println("Exception in /profile/{id} route: ${e.message}") // 예외 확인
                e.printStackTrace()
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }

        put("/{id}/nickname") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val changedName = call.request.queryParameters["changedName"]

                if (id == null) {
                    call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
                    return@put
                }

                val user = UserStorage.findUserById(id)
                if (user == null) {
                    call.respondText("User not found", status = HttpStatusCode.NotFound)
                    return@put
                }

                if (changedName.isNullOrBlank()) {
                    call.respondText("Nickname cannot be null or empty.", status = HttpStatusCode.BadRequest)
                    return@put
                }

                if (changedName.length > 20) { // 예시: 닉네임 최대 길이 10
                    call.respondText("Nickname exceeds maximum length.", status = HttpStatusCode.BadRequest)
                    return@put
                }

                if (UserStorage.users.any { it.nickname == changedName && it.id != id }) {
                    call.respondText("Nickname already exists.", status = HttpStatusCode.Conflict)
                    return@put
                }

                user.nickname = changedName
                call.respondText("Nickname updated successfully.", status = HttpStatusCode.OK)

            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }

        put("/{id}/genre") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                val genresString = call.request.queryParameters["genres"]

                if (id == null) {
                    call.respondText("Invalid ID", status = HttpStatusCode.BadRequest)
                    return@put
                }

                val user = UserStorage.findUserById(id)
                if (user == null) {
                    call.respondText("User not found", status = HttpStatusCode.NotFound)
                    return@put
                }

                if (genresString.isNullOrBlank()) {
                    user.likedGenres = mutableListOf()
                    call.respondText("Liked genres cleared.", status = HttpStatusCode.OK)
                    return@put
                }

                val newLikedGenres = try {
                    genresString.split(",").map { it.trim().toInt() }.toMutableList()
                } catch (e: NumberFormatException) {
                    call.respondText("Invalid genre format. Please provide comma-separated numbers.", status = HttpStatusCode.BadRequest)
                    return@put
                }

                user.likedGenres = newLikedGenres
                call.respondText("Liked genres updated successfully.", status = HttpStatusCode.OK)

            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }

        get("/list") {
            try {
                val users = UserStorage.users
                call.respond(users)
            } catch (e: Exception) {
                call.respondText("Internal Server Error: ${e.message}", status = HttpStatusCode.InternalServerError)
                e.printStackTrace()
            }
        }
    }
}