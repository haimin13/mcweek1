package org.example.storage

import org.example.models.Playlist
import java.time.LocalDateTime

object PlaylistStorage {
    private val playlists = mutableListOf(
        Playlist(
            id = 1,
            title = "Chill Vibes",
            author = "user1",
            thumbnailId = 1,
            keywords = mutableListOf("chill", "relax"),
            likedBy = mutableListOf(1, 2, 3),
            songIds = mutableListOf(101, 102),
            visibility = 0,
            createdAt = "2023-01-01T10:00:00",
            ranking = 1
        ),
        Playlist(
            id = 2,
            title = "Workout Mix",
            author = "user2",
            thumbnailId = 2,
            keywords = mutableListOf("workout", "energy"),
            likedBy = mutableListOf(1, 3),
            songIds = mutableListOf(103, 104),
            visibility = 0,
            createdAt = "2023-01-05T15:30:00",
            ranking = 2
        ),
        Playlist(
            id = 3,
            title = "Focus Music",
            author = "user1",
            thumbnailId = 3,
            keywords = mutableListOf("focus", "study"),
            likedBy = mutableListOf(2),
            songIds = mutableListOf(105),
            visibility = 0,
            createdAt = "2023-01-10T09:00:00",
            ranking = 3
        )
    )

    fun add(playlist: Playlist) {
        playlists.add(playlist)
    }
    fun getByUser(id: Int, type: String): List<Playlist> = playlists

    fun findPlaylistById(id: Int): Playlist? = playlists.find { it.id == id }

//    fun getByUser(id: Int, type: String): List<Playlist> {
//        return when (type) {
//            "liked" -> playlists.filter { it.likedBy?.contains(id) == true }
//            "created" -> playlists.filter { it.author == "jaehyeon" } // 예: 테스트용 계정
//            else -> playlists
//        }
//    }
//    fun getByUser(id: Int, type: String): List<Playlist> {
//        return when (type) {
//            "liked" -> playlists.filter { it.likedBy?.contains(id) == true }
//            "created" -> playlists.filter { it.author == "user_$id" } // 예시: 유저명을 "user_1" 등으로 저장한다고 가정
//            else -> playlists.filter { it.likedBy?.contains(id) == true || it.author == "user_$id" }
//        }
//    }
}
