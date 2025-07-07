package com.example.myapplication1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.list.FriendsUpdateList
import com.example.myapplication1.ui.components.models.UserLog

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
    myId: Int = 0
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        FriendsUpdateList(userLogs = dummyUserLogs)
    }
}