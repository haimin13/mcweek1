package org.example.storage

import org.example.models.*
import java.time.LocalDateTime

object UserStorage {
  val users = mutableListOf(
    User(1, "jaehyeon", 10, mutableListOf(2, 3, 4, 5, 6, 7, 8, 9), mutableListOf(2), mutableListOf(1, 2), mutableListOf(1, 3), mutableListOf(1), mutableListOf(1)),
    User(2, "minji", 11, mutableListOf(1, 3), mutableListOf(1), mutableListOf(2, 3), mutableListOf(2), mutableListOf(1, 2), mutableListOf(2)),
    User(3, "jisu", 12, mutableListOf(1, 2), mutableListOf(), mutableListOf(3), mutableListOf(3), mutableListOf(2), mutableListOf(3)),
    User(4, "alex", 13),
    User(5, "sophie", 14),
    User(6, "mike", 15),
    User(7, "emma", 16),
    User(8, "john", 17),
    User(9, "lisa", 18),
    User(10, "kevin", 19)
  )
}

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
    Genre(1, "Pop"),
    Genre(2, "Rock"),
    Genre(3, "Hip-hop"),
    Genre(4, "Jazz"),
    Genre(5, "Classical"),
    Genre(6, "Electronic"),
    Genre(7, "Country"),
    Genre(8, "Reggae"),
    Genre(9, "R&B"),
    Genre(10, "Indie")
  )
}

object ArtistStorage {
  val artists = mutableListOf(
    Artist(1, "IU", 21, mutableListOf(1, 9), mutableListOf(1, 2)),
    Artist(2, "BTS", 22, mutableListOf(1, 3), mutableListOf(3, 4)),
    Artist(3, "Adele", 23, mutableListOf(1, 5), mutableListOf(5)),
    Artist(4, "Drake", 24, mutableListOf(3, 9), mutableListOf(6)),
    Artist(5, "Coldplay", 25, mutableListOf(2), mutableListOf(7)),
    Artist(6, "Beethoven", 26, mutableListOf(5), mutableListOf(8)),
    Artist(7, "SZA", 27, mutableListOf(9), mutableListOf(9)),
    Artist(8, "Zico", 28, mutableListOf(3), mutableListOf(10)),
    Artist(9, "Dean", 29, mutableListOf(9, 10), mutableListOf(11)),
    Artist(10, "Lauv", 30, mutableListOf(1), mutableListOf(12))
  )
}
