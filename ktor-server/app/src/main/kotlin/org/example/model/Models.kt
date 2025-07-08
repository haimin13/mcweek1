package org.example.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class Playlist(
    val id: Int,
    val title: String,
    val author: String,
    val thumbnailId: Int? = null,
    val keywords: MutableList<String>? = null,
    val likedBy: MutableList<Int>? = null,
    val songIds: MutableList<Int>? = null,
    val visibility: Int = 0,
    val createdAt: String,  // 꼭 String으로!
    val ranking: Int? = null
)

@Serializable
data class User(
    val id: Int,
    var nickname: String,
    val thumbnailId: Int? = null,
    val friends: MutableList<Int>? = null,
    val closeFriends: MutableList<Int>? = null,
    var likedGenres: MutableList<Int>? = null,
    val likedSongs: MutableList<Int>? = null,
    val likedPlaylists: MutableList<Int>? = null,
    val likedArtists: MutableList<Int>? = null,
    val createdPlaylists: MutableList<Int>? = null,
)
@Serializable
data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val length: String,
    val thumbnailId: Int? = null,
    val genres: MutableList<Int>? = null,
    val likedBy: MutableList<Int>? = null,
    val includedIn: MutableList<Int>? = null,
    val ranking: Int? = null
)

@Serializable
data class Artist(
    val id: Int,
    val nickname: String,
    val thumbnailId: Int? = null,
    val likedGenres: MutableList<Int>? = null,
    val likedBy: MutableList<Int>? = null,
    val songsId: MutableList<Int>? = null
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
data class UserLog(
    val userId: Int,
    val userName: String,
    val itemId: Int,
    val itemName: String,
    val itemType: String,
    val likedTime: String
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)

@Serializable
data class ProfileSong(
    val id: Int,
    val title: String,
    val artist: String
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