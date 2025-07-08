package org.example.storage

import org.example.models.Genre

object GenreStorage {
    private val genres = mutableListOf(
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