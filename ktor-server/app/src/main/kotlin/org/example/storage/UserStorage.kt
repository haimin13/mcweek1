package org.example.storage

import org.example.models.User


object UserStorage {
    private val users = mutableListOf(
        User(
            id = 1,
            nickname = "user1",
            thumbnailId = 1,
            friends = mutableListOf(2, 3, 4, 5, 6, 7),
            closeFriends = mutableListOf(4, 5),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 2,
            nickname = "user2",
            thumbnailId = 2,
            friends = mutableListOf(1, 3, 6, 8, 9),
            closeFriends = mutableListOf(1, 3),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 3,
            nickname = "user3",
            thumbnailId = 3,
            friends = mutableListOf(1, 2, 4, 7, 10),
            closeFriends = mutableListOf(1, 2),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 4,
            nickname = "user4",
            thumbnailId = 4,
            friends = mutableListOf(1, 3, 5, 6, 11),
            closeFriends = mutableListOf(1, 5),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 5,
            nickname = "user5",
            thumbnailId = 5,
            friends = mutableListOf(1, 4, 6, 7, 8),
            closeFriends = mutableListOf(1, 4),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 6,
            nickname = "user6",
            thumbnailId = 6,
            friends = mutableListOf(1, 2, 4, 5, 9),
            closeFriends = mutableListOf(2, 5),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 7,
            nickname = "user7",
            thumbnailId = 7,
            friends = mutableListOf(1, 3, 5, 8, 10),
            closeFriends = mutableListOf(3, 5),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 8,
            nickname = "user8",
            thumbnailId = 8,
            friends = mutableListOf(2, 5, 7, 9, 11),
            closeFriends = mutableListOf(5, 7),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 9,
            nickname = "user9",
            thumbnailId = 9,
            friends = mutableListOf(2, 6, 8, 10, 11),
            closeFriends = mutableListOf(6, 8),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 10,
            nickname = "user10",
            thumbnailId = 10,
            friends = mutableListOf(3, 7, 9, 11),
            closeFriends = mutableListOf(7, 9),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        ),
        User(
            id = 11,
            nickname = "user11",
            thumbnailId = 11,
            friends = mutableListOf(4, 8, 9, 10),
            closeFriends = mutableListOf(8, 10),
            likedGenres = mutableListOf(1, 3),
            likedSongs = mutableListOf(101, 102),
            likedPlaylists = mutableListOf(1, 3),
            likedArtists = mutableListOf(1, 3),
            createdPlaylists = mutableListOf(1, 3)
        )
    )
    fun getUsers(): MutableList<User> = users

    fun add(user: User) {
        users.add(user)
    }
    fun findUserById(id: Int): User? {
        return users.find { it.id == id }
    }
    fun addFriend(id: Int, targetId: Int): Boolean {
        val user = users.find { it.id == id }
        val targetUser = users.find { it.id == targetId }
        if (user != null && targetUser != null) {
            user.friends?.add(targetId)
            return true
        }
        else return false
    }
    fun searchFriend(id: Int, targetString: String?): Int {
        val user = users.find { it.id == id }
        if (user == null) { return 0 } // 요청 유저가 존재하지 않음

        if (targetString == null || targetString.isBlank()) { return 1 } // 검색할 닉네임이 없음

        // nickname으로 대상 유저 찾기
        val targetUser = users.find { it.nickname == targetString }
        if (targetUser == null) { return 2 } // 대상 유저가 존재하지 않음

        val targetId = targetUser.id

        if (id == targetId) { return 3 } // 자기 자신을 친구로 추가하려는 경우

        if (user.friends?.contains(targetId) == true) { return 4 } // 이미 친구인 경우

        return 5  // 친구 추가 가능
    }
    fun removeFriend(id: Int, targetId: Int): Boolean {
        val user = users.find { it.id == id }
        val targetUser = users.find { it.id == targetId }
        if (user != null && targetUser != null) {
            val removedFromFriends = user.friends?.remove(targetId) ?: false
            val removedFromCloseFriends = user.closeFriends?.remove(targetId) ?: false
            return removedFromFriends || removedFromCloseFriends
        }
        return false
    }
    fun addCloseFriend(id: Int, targetId: Int): Boolean {
        val user = users.find { it.id == id }
        val targetUser = users.find { it.id == targetId }
        if (user != null && targetUser != null) {
            // 이미 친구인지 확인
            if (user.friends?.contains(targetId) == true) {
                // 친한 친구 목록에 추가
                if (user.closeFriends?.contains(targetId) != true) {
                    user.closeFriends?.add(targetId)
                    return true
                }
            }
        }
        return false
    }

    fun removeCloseFriend(id: Int, targetId: Int): Boolean {
        val user = users.find { it.id == id }
        val targetUser = users.find { it.id == targetId }
        if (user != null && targetUser != null) {
            // 친한 친구 목록에서 제거
            return user.closeFriends?.remove(targetId) ?: false
        }
        return false
    }
}