package org.example.storage

import org.example.models.Song

object SongStorage {
  val songs = mutableListOf<Song>(
    Song(
        id = 1,
        title = "Dreamers",
        artist = "Jung Kook",
        length = "3:24",
        thumbnailId = 101,
        genres = mutableListOf(1, 3),
        likedBy = mutableListOf(1, 2, 3),
        includedIn = mutableListOf(10, 20),
        ranking = 1
    ),
    Song(
        id = 2,
        title = "Rush Hour",
        artist = "Crush",
        length = "3:05",
        thumbnailId = 102,
        genres = mutableListOf(2, 4),
        likedBy = mutableListOf(2, 3),
        includedIn = mutableListOf(10),
        ranking = 2
    ),
    Song(
        id = 3,
        title = "Butter",
        artist = "BTS",
        length = "2:45",
        thumbnailId = 103,
        genres = mutableListOf(1),
        likedBy = mutableListOf(1, 3, 4),
        includedIn = mutableListOf(30),
        ranking = 3
    ),
    Song(
        id = 4,
        title = "Ditto",
        artist = "NewJeans",
        length = "3:10",
        thumbnailId = 104,
        genres = mutableListOf(3),
        likedBy = mutableListOf(2, 4, 5),
        includedIn = mutableListOf(40),
        ranking = 4
    ),
    Song(
        id = 5,
        title = "LOVE DIVE",
        artist = "IVE",
        length = "2:58",
        thumbnailId = 105,
        genres = mutableListOf(3, 5),
        likedBy = mutableListOf(3, 5),
        includedIn = mutableListOf(50),
        ranking = 5
    ),
    Song(
        id = 6,
        title = "I AM",
        artist = "IVE",
        length = "3:30",
        thumbnailId = 106,
        genres = mutableListOf(5),
        likedBy = mutableListOf(6),
        includedIn = mutableListOf(50, 51),
        ranking = 6
    ),
    Song(
        id = 7,
        title = "OMG",
        artist = "NewJeans",
        length = "3:15",
        thumbnailId = 107,
        genres = mutableListOf(3),
        likedBy = mutableListOf(2, 3, 6),
        includedIn = mutableListOf(40),
        ranking = 7
    ),
    Song(
        id = 8,
        title = "Seven",
        artist = "Jung Kook",
        length = "3:00",
        thumbnailId = 108,
        genres = mutableListOf(1, 6),
        likedBy = mutableListOf(1, 2),
        includedIn = mutableListOf(10, 52),
        ranking = 8
    ),
    Song(
        id = 9,
        title = "Spring Day",
        artist = "BTS",
        length = "4:34",
        thumbnailId = 109,
        genres = mutableListOf(1, 7),
        likedBy = mutableListOf(4, 5),
        includedIn = mutableListOf(30, 53),
        ranking = 9
    ),
    Song(
        id = 10,
        title = "Attention",
        artist = "NewJeans",
        length = "3:02",
        thumbnailId = 110,
        genres = mutableListOf(3, 5),
        likedBy = mutableListOf(1, 2, 3, 4),
        includedIn = mutableListOf(40),
        ranking = 10
    )
  )
}
