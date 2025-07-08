package org.example.data

object UserRepository {
    private val users = listOf(
        User(
            id = 1,
            nickname = "user1",
            thumbnailId = 1,
            friends = listOf(2, 3, 4, 5, 6, 7),
            closeFriends = listOf(4, 5),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 2,
            nickname = "user2",
            thumbnailId = 2,
            friends = listOf(1, 3, 6, 8, 9),
            closeFriends = listOf(1, 3),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 3,
            nickname = "user3",
            thumbnailId = 3,
            friends = listOf(1, 2, 4, 7, 10),
            closeFriends = listOf(1, 2),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 4,
            nickname = "user4",
            thumbnailId = 4,
            friends = listOf(1, 3, 5, 6, 11),
            closeFriends = listOf(1, 5),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 5,
            nickname = "user5",
            thumbnailId = 5,
            friends = listOf(1, 4, 6, 7, 8),
            closeFriends = listOf(1, 4),
            likedGenres = emptyList(),
            likedSongs = empty,
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 6,
            nickname = "user6",
            thumbnailId = 6,
            friends = listOf(1, 2, 4, 5, 9),
            closeFriends = listOf(2, 5),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 7,
            nickname = "user7",
            thumbnailId = 7,
            friends = listOf(1, 3, 5, 8, 10),
            closeFriends = listOf(3, 5),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 8,
            nickname = "user8",
            thumbnailId = 8,
            friends = listOf(2, 5, 7, 9, 11),
            closeFriends = listOf(5, 7),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 9,
            nickname = "user9",
            thumbnailId = 9,
            friends = listOf(2, 6, 8, 10, 11),
            closeFriends = listOf(6, 8),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 10,
            nickname = "user10",
            thumbnailId = 10,
            friends = listOf(3, 7, 9, 11),
            closeFriends = listOf(7, 9),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        ),
        User(
            id = 11,
            nickname = "user11",
            thumbnailId = 11,
            friends = listOf(4, 8, 9, 10),
            closeFriends = listOf(8, 10),
            likedGenres = emptyList(),
            likedSongs = emptyList(),
            likedPlaylists = emptyList(),
            likedArtists = emptyList(),
            createdPlaylists = emptyList()
        )
    )

    fun getUsers(): List<User> = users

    fun findUserById(id: Int): User? = users.find { it.id == id }
}
