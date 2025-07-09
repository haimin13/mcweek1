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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.dialog.AddFriendDialog
import androidx.compose.ui.graphics.Color
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import com.example.myapplication1.data.model.FriendInfo
import com.example.myapplication1.data.repository.FriendsRepository
import com.example.myapplication1.data.repository.ProfileRepository
import kotlinx.coroutines.launch

@Composable
fun FriendsTabMain(
    onNotificationClick: () -> Unit,
    myId: Int
) {
    val scope = rememberCoroutineScope()
    var friendsInfos by remember { mutableStateOf(listOf<FriendInfo>()) }
    var favoriteFriendsInfos by remember { mutableStateOf(listOf<FriendInfo>()) }
    var myNickname by remember { mutableStateOf("") }
    LaunchedEffect(myId) {
        scope.launch { // 코루틴 스코프 내에서 API 호출
            try {
                val profile = ProfileRepository().getProfile(myId)
                                myNickname = profile.nickname ?: ""
            } catch (e: Exception) {
                println("Error fetching my nickname for ID $myId: ${e.message}")
                myNickname = ""
            }
        }
    }
    LaunchedEffect(myId) {
        scope.launch { // 코루틴 스코프 내에서 API 호출
            try {
                friendsInfos = FriendsRepository().getFriends(myId, "all")

            } catch (e: Exception) {
                println("Error fetching profile for ID $myId: ${e.message}")
                friendsInfos = listOf() // 오류 발생 시 프로필 초기화
            }
        }
    }
    LaunchedEffect(myId) {
        scope.launch { // 코루틴 스코프 내에서 API 호출
            try {
                favoriteFriendsInfos = FriendsRepository().getFriends(myId, "close")

            } catch (e: Exception) {
                println("Error fetching profile for ID $myId: ${e.message}")
                favoriteFriendsInfos = listOf() // 오류 발생 시 프로필 초기화
            }
        }
    }

    var showAddFriendDialog by remember { mutableStateOf(false) }

    fun handleLikeToggle(friendInfo: FriendInfo, isLiked: Boolean) {
        scope.launch {
            if (!isLiked) {
                // 하트를 눌렀을 때 close friends에 추가
                val result = FriendsRepository().addCloseFriend(myId, friendInfo.friendId)
                if (result.success) {
                    // favoriteFriendsInfos에 추가
                    val updatedFriend = friendInfo.copy(isCloseFriend = true)
                    favoriteFriendsInfos = favoriteFriendsInfos + updatedFriend

                    // friendsInfos에서 해당 친구의 상태 업데이트
                    friendsInfos = friendsInfos.map { friend ->
                        if (friend.friendId == friendInfo.friendId) {
                            friend.copy(isCloseFriend = true)
                        } else {
                            friend
                        }
                    }
                }
            } else {
                // 하트를 해제했을 때 close friends에서 제거
                val result = FriendsRepository().removeCloseFriend(myId, friendInfo.friendId)
                if (result.success) {
                    // favoriteFriendsInfos에서 제거
                    favoriteFriendsInfos = favoriteFriendsInfos.filter { it.friendId != friendInfo.friendId }

                    // friendsInfos에서 해당 친구의 상태 업데이트
                    friendsInfos = friendsInfos.map { friend ->
                        if (friend.friendId == friendInfo.friendId) {
                            friend.copy(isCloseFriend = false)
                        } else {
                            friend
                        }
                    }
                }
            }
        }
    }

    fun removeFriend(target: FriendInfo) {
        scope.launch {
            val result = FriendsRepository().removeFriend(myId, target.friendId)
            if (result.success) {
                friendsInfos = friendsInfos.filter { it != target }
                favoriteFriendsInfos = favoriteFriendsInfos.filter { it != target }
            }
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
                        items = favoriteFriendsInfos,
                        key = { friendInfo -> friendInfo.friendId }
                    ) { info ->
                        GalleryEntry(
                            contentId = info.friendId,
                            contentName = info.nickname,
                            showText = true,
                            isLiked = info.isCloseFriend,
                            onLikeToggle = { ->
                                handleLikeToggle(info, info.isCloseFriend)
                            },
                            onRemoveFriend = { -> // 이 부분 추가
                                removeFriend(info)
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
                        items = friendsInfos,
                        key = { friendInfo -> friendInfo.friendId }
                    ) { info ->
                        GalleryEntry(
                            contentId = info.friendId,
                            contentName = info.nickname,
                            showText = true,
                            isLiked = info.isCloseFriend,
                            onLikeToggle = { ->
                                handleLikeToggle(info, info.isCloseFriend)
                            },
                            onRemoveFriend = { -> // 이 부분 추가
                                removeFriend(info)
                            }
                        )
                    }
                }
            }
        }
        /*FloatingActionButton(
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
        }*/
    }
    /*AddFriendDialog(
        showDialog = showAddFriendDialog,
        myNickname = myNickname,
        currentFriends = friendsInfos,
        onDismiss = { showAddFriendDialog = false },
        onAddFriend = { nickname ->
            // 친구 추가 로직
            friendsIds = friendsIds + nickname
        },
        onSearchUser = { nickname ->
            // TODO: Implement actual asynchronous API call for user search
            // For now, returning true as a placeholder.
            // The AddFriendDialog's onSearchUser signature might need to be
            // changed to a suspend function or accept a callback for proper async handling.
            true
        }
    )*/
}