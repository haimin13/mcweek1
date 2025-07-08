package org.example.storage

import org.example.models.Artist

object ArtistStorage {
    val artists = mutableListOf(
        Artist(1, "IU", 21, mutableListOf(1, 9), mutableListOf(1, 2), mutableListOf(101, 102)),
        Artist(2, "BTS", 22, mutableListOf(1, 3), mutableListOf(3, 4), mutableListOf(101, 102)),
        Artist(3, "Adele", 23, mutableListOf(1, 5), mutableListOf(5), mutableListOf(101, 102)),
        Artist(4, "Drake", 24, mutableListOf(3, 9), mutableListOf(6), mutableListOf(101, 102)),
        Artist(5, "Coldplay", 25, mutableListOf(2), mutableListOf(7), mutableListOf(101, 102)),
        Artist(6, "Beethoven", 26, mutableListOf(5), mutableListOf(8), mutableListOf(101, 102)),
        Artist(7, "SZA", 27, mutableListOf(9), mutableListOf(9), mutableListOf(101, 102)),
        Artist(8, "Zico", 28, mutableListOf(3), mutableListOf(10), mutableListOf(101, 102)),
        Artist(9, "Dean", 29, mutableListOf(9, 10), mutableListOf(11), mutableListOf(101, 102)),
        Artist(10, "Lauv", 30, mutableListOf(1), mutableListOf(12), mutableListOf(101, 102))
    )

    fun getAllArtists(): MutableList<Artist> = artists

    fun findArtistById(id: Int): Artist? = artists.find { it.id == id }
}