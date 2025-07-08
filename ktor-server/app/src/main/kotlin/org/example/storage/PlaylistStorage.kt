package org.example.storage

import org.example.models.Playlist
import java.time.LocalDateTime

object PlaylistStorage {
    val playlists = mutableListOf<Playlist>()

    fun add(playlist: Playlist) {
        playlists.add(playlist)
    }

    fun getByUser(id: Int, type: String): List<Playlist> {
        return when (type) {
            "liked" -> playlists.filter { it.likedBy?.contains(id) == true }
            "created" -> playlists.filter { it.author == "user_$id" } // 예시: 유저명을 "user_1" 등으로 저장한다고 가정
            else -> playlists.filter { it.likedBy?.contains(id) == true || it.author == "user_$id" }
        }
    }
}
