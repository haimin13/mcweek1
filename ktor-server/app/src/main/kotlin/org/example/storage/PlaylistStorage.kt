package org.example.storage

import org.example.models.Playlist
import java.time.LocalDateTime

object PlaylistStorage {
    val playlists = mutableListOf(
        Playlist(
            id = 1,
            title = "감성 팝",
            author = 1,
            thumbnailId = 201,
            keywords = mutableListOf("감성", "팝", "밤산책"),
            likedBy = mutableListOf(2, 3),
            songIds = mutableListOf(1, 3, 5),
            visibility = 0,
            createdAt = "2024-06-01T12:00:00",
            ranking = 1
        ),
        Playlist(
            id = 2,
            title = "아침에 듣기 좋은 노래",
            author = 2,
            thumbnailId = 202,
            keywords = mutableListOf("모닝루틴", "활력"),
            likedBy = mutableListOf(1, 3, 4),
            songIds = mutableListOf(2, 4),
            visibility = 0,
            createdAt = "2024-06-02T08:30:00",
            ranking = 2
        ),
        Playlist(
            id = 3,
            title = "운동할 때 듣기 좋은 곡",
            author = 7,
            thumbnailId = 203,
            keywords = mutableListOf("헬스", "EDM"),
            likedBy = mutableListOf(5),
            songIds = mutableListOf(6, 7, 8),
            visibility = 0,
            createdAt = "2024-06-05T14:10:00",
            ranking = 3
        ),
        Playlist(
            id = 4,
            title = "퇴근길 힐링",
            author = 3,
            thumbnailId = 204,
            keywords = mutableListOf("퇴근", "힐링", "드라이브"),
            likedBy = mutableListOf(1),
            songIds = mutableListOf(9, 10),
            visibility = 0,
            createdAt = "2024-06-07T19:00:00",
            ranking = 4
        ),
        Playlist(
            id = 5,
            title = "K-POP 히트곡 모음",
            author = 9,
            thumbnailId = 205,
            keywords = mutableListOf("K-POP", "차트"),
            likedBy = mutableListOf(2, 3, 4, 5),
            songIds = mutableListOf(3, 5, 7),
            visibility = 0,
            createdAt = "2024-06-10T09:00:00",
            ranking = 5
        ),
        Playlist(
            id = 6,
            title = "뉴진스 특집",
            author = 4,
            thumbnailId = 206,
            keywords = mutableListOf("NewJeans", "하입"),
            likedBy = mutableListOf(1, 2),
            songIds = mutableListOf(4, 7, 10),
            visibility = 0,
            createdAt = "2024-06-12T11:11:00",
            ranking = 6
        ),
        Playlist(
            id = 7,
            title = "비 오는 날 감성곡",
            author = 5,
            thumbnailId = 207,
            keywords = mutableListOf("비", "잔잔한", "우울"),
            likedBy = mutableListOf(1, 6),
            songIds = mutableListOf(1, 9),
            visibility = 0,
            createdAt = "2024-06-14T17:40:00",
            ranking = 7
        ),
        Playlist(
            id = 8,
            title = "밤 드라이브",
            author = 1,
            thumbnailId = 208,
            keywords = mutableListOf("드라이브", "밤", "조용한"),
            likedBy = mutableListOf(3, 4),
            songIds = mutableListOf(2, 6, 8),
            visibility = 0,
            createdAt = "2024-06-16T22:00:00",
            ranking = 8
        ),
        Playlist(
            id = 9,
            title = "명곡 모음집",
            author = 6,
            thumbnailId = 209,
            keywords = mutableListOf("명곡", "추천"),
            likedBy = mutableListOf(1, 2, 3, 4, 5),
            songIds = mutableListOf(3, 4, 5, 6),
            visibility = 0,
            createdAt = "2024-06-18T15:30:00",
            ranking = 9
        ),
        Playlist(
            id = 10,
            title = "여름 플레이리스트",
            author = 2,
            thumbnailId = 210,
            keywords = mutableListOf("여름", "시원한", "바캉스"),
            likedBy = mutableListOf(6),
            songIds = mutableListOf(1, 2, 7),
            visibility = 0,
            createdAt = "2024-06-20T13:00:00",
            ranking = 10
        )
    )


    fun add(playlist: Playlist) {
        playlists.add(playlist)
    }

    fun getByUser(id: Int, type: String): List<Playlist> {
        return when (type) {
            "liked" -> playlists.filter { it.likedBy?.contains(id) == true }
            "created" -> playlists.filter { it.author == id } // 예시: 유저명을 "user_1" 등으로 저장한다고 가정
            else -> playlists.filter { it.likedBy?.contains(id) == true || it.author == id }
        }
    }
}
