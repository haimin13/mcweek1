package org.example.storage

import org.example.models.UserLog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object UserLogStorage {
    private fun generateRandomDateTime(): String {
        val year = 2025
        val month = Random.nextInt(1, 13)
        val day = Random.nextInt(1, 29) // Simplistic, avoids month-end issues
        val hour = Random.nextInt(0, 24)
        val minute = Random.nextInt(0, 60)
        val second = Random.nextInt(0, 60)
        return LocalDateTime.of(year, month, day, hour, minute, second).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    private val userLogs = mutableListOf(
        UserLog(userId = 1, userName = "user1", itemId = 101, itemName = "Song A", itemType = "Song", likedTime = generateRandomDateTime()),
        UserLog(userId = 2, userName = "user2", itemId = 201, itemName = "Playlist X", itemType = "Playlist", likedTime = generateRandomDateTime()),
        UserLog(userId = 1, userName = "user1", itemId = 102, itemName = "Song B", itemType = "Song", likedTime = generateRandomDateTime()),
        UserLog(userId = 3, userName = "user3", itemId = 301, itemName = "Artist M", itemType = "Artist", likedTime = generateRandomDateTime()),
        UserLog(userId = 2, userName = "user2", itemId = 202, itemName = "Song C", itemType = "Song", likedTime = generateRandomDateTime())
    )

    fun getUserLogs(): MutableList<UserLog> = userLogs
}