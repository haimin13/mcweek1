package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.models.UserLog


@Composable
fun FriendsUpdateList(modifier: Modifier = Modifier, userLogs: List<UserLog>, verticalSpacing: Int = 12) {
    GenericList(
        modifier = modifier,
        items = userLogs,
        verticalSpacing = verticalSpacing.dp,
//            onItemClick = { playlist ->
//                navController.navigate("playlistDetail/${playlist.id}")
//            }
    ) { userLog: UserLog ->
        FriendsUpdateEntry(userLog)
    }
}
