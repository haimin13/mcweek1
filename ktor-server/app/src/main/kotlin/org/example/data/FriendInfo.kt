package org.example.data

import kotlinx.serialization.Serializable

@Serializable
data class FriendInfo(
    val friendId: Int,
    val nickname: String,
    val thumbnailId: Int?,
    val isCloseFriend: Boolean
)
