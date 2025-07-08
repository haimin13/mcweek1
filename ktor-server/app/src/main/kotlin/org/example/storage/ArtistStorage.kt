package org.example.storage

import org.example.models.Artist

object ArtistStorage {
    private val artists = mutableListOf(
        Artist(id = 1, nickname = "Artist A", thumbnailId = 1, likedGenres = mutableListOf(1, 3), likedBy = mutableListOf(1, 2), songsId = mutableListOf(101, 102)),
        Artist(id = 2, nickname = "Artist B", thumbnailId = 2, likedGenres = mutableListOf(2, 4), likedBy = mutableListOf(3), songsId = mutableListOf(103, 104)),
        Artist(id = 3, nickname = "Artist C", thumbnailId = 3, likedGenres = mutableListOf(5), likedBy = mutableListOf(1), songsId = mutableListOf(105))
    )

    fun getAllArtists(): MutableList<Artist> = artists

    fun findArtistById(id: Int): Artist? = artists.find { it.id == id }
}