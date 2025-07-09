package com.example.myapplication1.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.R
import com.example.myapplication1.data.model.Profile
import com.example.myapplication1.data.model.User
import com.example.myapplication1.data.repository.ProfileRepository
import com.example.myapplication1.network.RetrofitInstance
import com.example.myapplication1.ui.components.Edit.EditNickname
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.list.TagList
import com.example.myapplication1.ui.components.profile.ProfileRowArtist
import com.example.myapplication1.ui.components.profile.ProfileRowPlaylist
import com.example.myapplication1.ui.components.profile.ProfileRowSong
//import com.example.myapplication1.ui.screens.playlists.FriendsFavorites
import com.example.myapplication1.ui.screens.playlists.playList
import com.example.myapplication1.ui.components.profile.UserSwitchDialog
import com.example.myapplication1.ui.remote.PlaylistViewModel
import com.example.myapplication1.ui.remote.ProfileViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

// Temp
val tempIdList = listOf(1,2,3,4,5,6,7,8,9)



@Composable
fun MyTabMain(
    myId: Int, // 외부에서 받음
    onMyIdChange: (Int) -> Unit, // myId 변경 콜백,
    viewModel: ProfileViewModel = viewModel()
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
    val tempProfile = Profile(1,"null", 1, listOf(), listOf(), listOf(), listOf())
    var showNicknameEditDialog by remember { mutableStateOf(false) }
    var showUserSwitchDialog by remember { mutableStateOf(false) } // 사용자 변경 다이얼로그
    var tempNickname by remember { mutableStateOf("") } // 임시 닉네임 저장

    var myId by remember { mutableIntStateOf(myId) }
    var profile by remember { mutableStateOf<Profile>(tempProfile) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(myId) {
        scope.launch { // 코루틴 스코프 내에서 API 호출
            try {
                // RetrofitClient.apiService를 통해 Profile 객체를 가져옵니다.
                profile = ProfileRepository().getProfile(myId)
                profile?.nickname?.let {
                    tempNickname = it // 가져온 닉네임으로 tempNickname 업데이트
                }
            } catch (e: Exception) {
                println("Error fetching profile for ID $myId: ${e.message}")
                profile = tempProfile // 오류 발생 시 프로필 초기화
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            /*Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        showUserSwitchDialog = true
                    }
            )*/
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
                    thumbnailResId = profile.thumbnailId.toThumbnailResId(),
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
                    text = profile.nickname,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            tempNickname = nicknames[myId]
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
                TagList(
                    tags = profile.likedGenres,
                    title = "Music Preference",
                    onTagsUpdate = { newTags ->
                        // 사용자의 장르 목록 업데이트
                        likedTags = likedTags.toMutableList().apply {
                            set(myId, newTags)
                        }
                    }
                )
            }
            ProfileRowSong(
                rowName = "Liked Songs",
                entryList = profile.likedSongs,
            )
            ProfileRowPlaylist(
                rowName = "Playlists",
                entryList = profile.createdPlaylists
            )
            ProfileRowArtist(
                rowName = "Favorite Artists",
                entryList = profile.likedArtists
            )
        }
    }
    if (showNicknameEditDialog) {
        EditNickname(
            showDialog = showNicknameEditDialog,
            initialNickname = profile.nickname,
            existingNicknames = nicknames,
            onNicknameUpdate = { newNickname ->
                nicknames = nicknames.toMutableList().apply {
                    set(myId, newNickname)
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

fun Int.toThumbnailResId(): Int {
    return when (this) {
        1 -> R.drawable.profile_1
        2 -> R.drawable.profile_2
        3 -> R.drawable.profile_3
        4 -> R.drawable.profile_4
        5 -> R.drawable.profile_5
        6 -> R.drawable.profile_6
        7 -> R.drawable.profile_7
        8 -> R.drawable.profile_8
        else -> R.drawable.profile_1
    }
}

