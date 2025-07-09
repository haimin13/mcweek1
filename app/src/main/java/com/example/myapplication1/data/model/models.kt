package com.example.myapplication1.data.model

import com.example.myapplication1.data.util.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual
import java.time.LocalDateTime

@Serializable
data class Playlist(
    val id: Int,
    val title: String,
    val author: Int,
    val thumbnailId: Int? = null,
    @Contextual val keywords: MutableList<String>? = null,
    @Contextual val likedBy: MutableList<Int>? = null,
    @Contextual val songIds: MutableList<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String,  // 꼭 String으로!
    val ranking: Int? = null
)

@Serializable
data class Song(
    val id: Int,
    val title: String,
    val artist: List<Int>,
    val length: String,
    val thumbnailId: Int? = null,
    @Contextual val genres: MutableList<Int>? = null,
    @Contextual val likedBy: MutableList<Int>? = null,
    @Contextual val includedIn: MutableList<Int>? = null,
    val ranking: Int? = null
)

@Serializable
data class User(
    val id: Int,
    var nickname: String,
    val thumbnailId: Int? = null,
    @Contextual val friends: MutableList<Int>? = null,
    @Contextual val closeFriends: MutableList<Int>? = null,
    @Contextual var likedGenres: MutableList<Int>? = null,
    @Contextual val likedSongs: MutableList<Int>? = null,
    @Contextual val likedPlaylists: MutableList<Int>? = null,
    @Contextual val likedArtists: MutableList<Int>? = null,
    @Contextual val createdPlaylists: MutableList<Int>? = null,
)

@Serializable
data class Artist(
    val id: Int,
    val nickname: String,
    val thumbnailId: Int? = null,
    @Contextual val likedGenres: MutableList<Int>? = null,
    @Contextual val likedBy: MutableList<Int>? = null,
    @Contextual val songsId: MutableList<Int>? = null
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)

@Serializable
data class UserLikeLog(
    val userId: Int,
    val itemType: String,  // "song" or "playlist"
    val itemId: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    val likedTime: LocalDateTime
)

@Serializable
data class FriendInfo(
    val friendId: Int,
    val nickname: String,
    val thumbnailId: Int?,
    val isCloseFriend: Boolean
)

@Serializable
data class SearchResponse(
    val success: Boolean,
    val message: String
)

@Serializable
data class ChartResponse(
    val songs: List<Song>,
    val playlists: List<Playlist>
)

@Serializable
data class ProfileSong(
    val id: Int,
    val title: String,
    val artist: List<Int>
)

@Serializable
data class ProfileArtist(
    val id: Int,
    val nickname: String
)

@Serializable
data class ProfilePlaylist(
    val id: Int,
    val title: String
)

@Serializable
data class Profile(
    val userId: Int,
    val nickname: String,
    val thumbnailId: Int?,
    val likedGenres: List<Int>?,
    val likedSongs: List<ProfileSong>?,
    val likedArtists: List<ProfileArtist>?,
    val createdPlaylists: List<ProfilePlaylist>?
)