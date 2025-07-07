package com.example.myapplication1.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.Edit.EditNickname
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.TagList
import com.example.myapplication1.ui.components.profile.ProfileRow
import com.example.myapplication1.ui.components.profile.UserSwitchDialog

// Temp
val tempIdList = listOf(1,2,3,4,5,6,7,8,9)




@Composable
fun MyTabMain(
    myId: Int, // 외부에서 받음
    onMyIdChange: (Int) -> Unit // myId 변경 콜백
) {
    var likedTags by remember {
        mutableStateOf(listOf(
            listOf(1,3,5,6,7,16,15,8,9),
            listOf(2,4,5,7),
            listOf(1,4,8,12),
            listOf(3,7,11,15),
            listOf(2,6,10,14),
            listOf(5,9,13,17),
            listOf(1,8,15),
            listOf(4,11,18)
        ))
    }

    var nicknames by remember {
        mutableStateOf(mutableListOf(
            "lil monkey",
            "jaedungg",
            "haimin13",
            "bot1",
            "bot2",
            "bot3",
            "bot4",
            "bot5"
        ))
    }

    var showNicknameEditDialog by remember { mutableStateOf(false) }
    var showUserSwitchDialog by remember { mutableStateOf(false) } // 사용자 변경 다이얼로그
    var tempNickname by remember { mutableStateOf("") } // 임시 닉네임 저장

    val safeMyId = if (myId < nicknames.size && myId < likedTags.size) myId else 0

    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        showUserSwitchDialog = true
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "My Profile",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Box() {
                GalleryEntry(
                    contentName = "",
                    showText = false,
                    imageSize = 200,
                    showIcon = false,
                    onTap = {}, onLongPress = {}
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .size(25.dp)
                        .clip(shape = RoundedCornerShape(90))
                        .background(color = Color.LightGray)
                        .clickable {},
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                        )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 10.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = nicknames[safeMyId],
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            tempNickname = nicknames[safeMyId]
                            showNicknameEditDialog = true
                        }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Music Preference",
                    fontWeight = FontWeight.Bold
                )
                TagList(
                    tags = likedTags[safeMyId],
                    onTagsUpdate = { newTags ->
                        // 사용자의 장르 목록 업데이트
                        likedTags = likedTags.toMutableList().apply {
                            set(safeMyId, newTags)
                        }
                    }
                )
            }
            ProfileRow(
                rowName = "Liked Songs",
                entryList = tempIdList,
                entryType = "Song"
            )
            ProfileRow(
                rowName = "Playlists",
                entryList = tempIdList,
                entryType = "Playlist"
            )
            ProfileRow(
                rowName = "Favorite Artists",
                entryList = tempIdList,
                entryType = "Artist"
            )
        }
    }
    if (showNicknameEditDialog) {
        EditNickname(
            showDialog = showNicknameEditDialog,
            initialNickname = nicknames[safeMyId],
            existingNicknames = nicknames,
            onNicknameUpdate = { newNickname ->
                nicknames = nicknames.toMutableList().apply {
                    set(safeMyId, newNickname)
                }
            },
            onDismiss = { showNicknameEditDialog = false }
        )
    }
    // 사용자 변경 다이얼로그
    if (showUserSwitchDialog) {
        UserSwitchDialog(
            currentUserId = myId,
            userList = nicknames,
            onUserSwitch = { newId ->
                onMyIdChange(newId) // 상위 컴포넌트에 myId 변경 알림
                showUserSwitchDialog = false
            },
            onDismiss = { showUserSwitchDialog = false }
        )
    }
}

