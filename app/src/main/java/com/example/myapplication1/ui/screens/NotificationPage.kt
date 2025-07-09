package com.example.myapplication1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.ui.components.list.FriendsUpdateList
import com.example.myapplication1.ui.components.models.UserLog
import com.example.myapplication1.ui.remote.FriendLogViewModel

val dummyUserLogs = listOf(
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
    UserLog(userName = "jaedungg", itemName = "Drowning"),
)

// db에서는 n개 까지만 저장하고 그 이후로는 오래된 순으로 버림
@Composable
fun NotificationPage(
    onBackClick: () -> Unit,
    myId: Int = 1,
    logViewModel: FriendLogViewModel = viewModel()
) {
    val friendsUpdate by logViewModel.friendLogs.observeAsState(emptyList())

    LaunchedEffect(logViewModel) {
        logViewModel.loadFriendLogs(myId)
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .clickable (onClick = onBackClick),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null
        )
        FriendsUpdateList(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            userLogs = friendsUpdate,
            verticalSpacing = 16)
    }
}