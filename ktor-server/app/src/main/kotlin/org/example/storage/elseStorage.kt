package org.example.storage

import org.example.models.*
import java.time.LocalDateTime

object UserLikeLogStorage {
  val logs = mutableListOf(
    UserLikeLog(1, "song", 1, LocalDateTime.now().minusDays(1)),
    UserLikeLog(2, "song", 1, LocalDateTime.now().minusDays(1)),
    UserLikeLog(3, "song", 2, LocalDateTime.now().minusDays(2)),
    UserLikeLog(4, "song", 2, LocalDateTime.now().minusDays(3)),
    UserLikeLog(1, "song", 3, LocalDateTime.now().minusDays(2)),
    UserLikeLog(5, "song", 4, LocalDateTime.now().minusDays(1)),
    UserLikeLog(2, "song", 5, LocalDateTime.now().minusDays(2)),
    UserLikeLog(6, "song", 6, LocalDateTime.now().minusDays(1)),
    UserLikeLog(1, "playlist", 101, LocalDateTime.now().minusDays(1)),
    UserLikeLog(3, "playlist", 102, LocalDateTime.now().minusDays(2)),
    UserLikeLog(4, "playlist", 103, LocalDateTime.now().minusDays(1)),
    UserLikeLog(2, "playlist", 101, LocalDateTime.now().minusDays(3)),
    UserLikeLog(5, "playlist", 104, LocalDateTime.now().minusDays(1)),
    UserLikeLog(1, "song", 7, LocalDateTime.now().minusDays(1)),
    UserLikeLog(3, "song", 8, LocalDateTime.now().minusDays(2)),
    UserLikeLog(2, "song", 9, LocalDateTime.now().minusDays(2)),
    UserLikeLog(4, "song", 10, LocalDateTime.now().minusDays(1)),
    UserLikeLog(5, "song", 1, LocalDateTime.now().minusDays(2)),
    UserLikeLog(6, "playlist", 105, LocalDateTime.now().minusDays(1)),
    UserLikeLog(1, "playlist", 106, LocalDateTime.now().minusDays(1)),
    UserLikeLog(2, "playlist", 107, LocalDateTime.now().minusDays(2)),
    UserLikeLog(3, "playlist", 108, LocalDateTime.now().minusDays(1)),
    UserLikeLog(4, "song", 2, LocalDateTime.now().minusDays(1)),
    UserLikeLog(5, "song", 3, LocalDateTime.now().minusDays(2)),
    UserLikeLog(6, "song", 4, LocalDateTime.now().minusDays(3)),
    UserLikeLog(1, "song", 5, LocalDateTime.now().minusDays(1)),
    UserLikeLog(2, "playlist", 109, LocalDateTime.now().minusDays(2)),
    UserLikeLog(3, "playlist", 110, LocalDateTime.now().minusDays(3)),
    UserLikeLog(4, "playlist", 111, LocalDateTime.now().minusDays(1)),
    UserLikeLog(5, "playlist", 112, LocalDateTime.now().minusDays(1))
  )
}

object GenreStorage {
  val genres = mutableListOf(
    Genre(id = 1, name = "R&B"),
    Genre(id = 2, name = "Hiphop"),
    Genre(id = 3, name = "Pop"),
    Genre(id = 4, name = "Rock"),
    Genre(id = 5, name = "Country"),
    Genre(id = 6, name = "Indie"),
    Genre(id = 7, name = "Idol"),
    Genre(id = 8, name = "Electronica"),
    Genre(id = 9, name = "Jazz"),
    Genre(id = 10, name = "Classics"),
    Genre(id = 11, name = "Blues"),
    Genre(id = 12, name = "Punk"),
    Genre(id = 13, name = "Reggae"),
    Genre(id = 14, name = "Latin"),
    Genre(id = 15, name = "Folk"),
    Genre(id = 16, name = "Trot"),
    Genre(id = 17, name = "Acoustic"),
    Genre(id = 18, name = "Ballad"),
    Genre(id = 19, name = "Crossover")
  )

  fun getAllGenres(): MutableList<Genre> = genres

  fun getGenreById(id: Int): Genre? = genres.find { it.id == id }
}


