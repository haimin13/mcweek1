package com.example.myapplication1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.myapplication1.ui.components.gallery.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.dialog.AddFriendDialog
import androidx.compose.ui.graphics.Color

@Composable
fun FriendsTabMain(
    onNotificationClick: () -> Unit,
    myId: Int = 0
) {
    // friendsIds는 내 친구 database에서 불러옴
    var favoriteFriendsIds by remember {
        mutableStateOf(listOf(
            "jaedungg", "haimin13", "bot3"
        ))
    }
    var friendsIds by remember {
        mutableStateOf(listOf(
            "jaedungg", "haimin13", "bot3",
            "bot4", "user3"
        ))
    }
    var showAddFriendDialog by remember { mutableStateOf(false) }

    var myNickname: String = "lil monkey"
    val allUsers = listOf(
        "lil monkey", "jaedungg", "haimin13", "bot1", "bot2", "bot3",
        "bot4", "bot5", "user1", "user2", "user3"
    )

    fun handleLikeToggle(friendName: String, isLiked: Boolean) {
        if (isLiked) {
            // 하트를 눌렀을 때 close friends에 추가
            if (!favoriteFriendsIds.contains(friendName)) {
                favoriteFriendsIds = favoriteFriendsIds + friendName
            }
        } else {
            // 하트를 해제했을 때 close friends에서 제거
            favoriteFriendsIds = favoriteFriendsIds.filter { it != friendName }
        }
    }

    fun removeFriend(friendId: String) {
        friendsIds = friendsIds.filter { it != friendId }
        favoriteFriendsIds = favoriteFriendsIds.filter { it != friendId }
    }

    // 친한 친구 토글 함수
    fun toggleCloseFriend(friendId: String, isClose: Boolean) {
        if (isClose) {
            // 친한 친구에 추가
            if (!favoriteFriendsIds.contains(friendId)) {
                favoriteFriendsIds = favoriteFriendsIds + friendId
            }
        } else {
            // 친한 친구에서 제거
            favoriteFriendsIds = favoriteFriendsIds.filter { it != friendId }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 10.dp)
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onNotificationClick() }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Title("Close friends")
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                        .defaultMinSize(minHeight = 125.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = favoriteFriendsIds,
                        key = { friendId -> friendId }
                    ) { id ->
                        GalleryEntry(
                            contentName = id,
                            showText = true,
                            isLiked = true,
                            onLikeToggle = { friendName, isLiked ->
                                handleLikeToggle(friendName, isLiked)
                            },
                            onRemoveFriend = { friendId -> // 이 부분 추가
                                removeFriend(friendId)
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Title("All friends")
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        items = friendsIds,
                        key = { friendId -> "all_$friendId" }
                    ) { id ->
                        GalleryEntry(
                            contentName = id,
                            imageSize = 88, // 이미지 크기를 줄임
                            showText = true,
                            isLiked = favoriteFriendsIds.contains(id),
                            onLikeToggle = { friendName, isLiked ->
                                handleLikeToggle(friendName, isLiked)
                            },
                            onRemoveFriend = { friendId -> // 이 부분 추가
                                removeFriend(friendId)
                            }
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { showAddFriendDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFE8EAF6)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "친구 추가",
                tint = Color(0xFF303F9F)
            )
        }
    }
    AddFriendDialog(
        showDialog = showAddFriendDialog,
        myNickname = myNickname,
        currentFriends = friendsIds,
        onDismiss = { showAddFriendDialog = false },
        onAddFriend = { nickname ->
            // 친구 추가 로직
            friendsIds = friendsIds + nickname
        },
        onSearchUser = { nickname ->
            // 사용자 존재 여부 확인 (실제로는 서버 API 호출)
            allUsers.contains(nickname)
        }
    )
}