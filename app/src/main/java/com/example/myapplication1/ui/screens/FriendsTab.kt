package com.example.myapplication1.ui.screens

import androidx.compose.foundation.clickable
import com.example.myapplication1.ui.components.gallery.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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


@Composable
fun FriendsTabMain(
    onNotificationClick: () -> Unit
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

    var showAddFriendDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onNotificationClick() }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "close friends",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favoriteFriendsIds) { id ->
                        GalleryEntry(
                            contentName = id,
                            showText = true,
                            isLiked = true, // close friends는 항상 좋아요 상태
                            onLikeToggle = { friendName, isLiked ->
                                handleLikeToggle(friendName, isLiked)
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "all friends",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(friendsIds) { id ->
                        GalleryEntry(
                            contentName = id,
                            imageSize = 90,
                            showText = true,
                            isLiked = favoriteFriendsIds.contains(id), // close friends 여부에 따라 하트 상태 결정
                            onLikeToggle = { friendName, isLiked ->
                                handleLikeToggle(friendName, isLiked)
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
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "친구 추가"
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