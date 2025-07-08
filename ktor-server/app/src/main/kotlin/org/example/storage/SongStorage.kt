package org.example.storage

import org.example.models.Song
import org.example.models.Artist

object SongStorage {
    private val songs = mutableListOf(
        Song(id = 101, title = "Song 1", artist = "Artist A", length = "3:30", thumbnailId = 101, genres = mutableListOf(1), likedBy = mutableListOf(1, 2), includedIn = mutableListOf(1, 2), ranking = null),
        Song(id = 102, title = "Song 2", artist = "Artist A", length = "4:00", thumbnailId = 102, genres = mutableListOf(3), likedBy = mutableListOf(1), includedIn = mutableListOf(1), ranking = null),
        Song(id = 103, title = "Song 3", artist = "Artist B", length = "2:45", thumbnailId = 103, genres = mutableListOf(2), likedBy = mutableListOf(3), includedIn = mutableListOf(2), ranking = null),
        Song(id = 104, title = "Song 4", artist = "Artist B", length = "3:15", thumbnailId = 104, genres = mutableListOf(4), likedBy = mutableListOf(1, 3), includedIn = mutableListOf(3), ranking = null),
        Song(id = 105, title = "Song 5", artist = "Artist C", length = "5:00", thumbnailId = 105, genres = mutableListOf(5), likedBy = mutableListOf(2), includedIn = mutableListOf(3), ranking = null)
    )

    fun getAllSongs(): MutableList<Song> = songs

    fun findSongById(id: Int): Song? = songs.find { it.id == id }
}