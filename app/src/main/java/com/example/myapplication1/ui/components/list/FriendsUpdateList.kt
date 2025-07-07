package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.models.UserLog


@Composable
fun FriendsUpdateList(modifier: Modifier = Modifier, userLogs: List<UserLog>) {
    GenericList(
        modifier = modifier.padding(horizontal = 20.dp),
        items = userLogs,
        verticalSpacing = 12.dp,
//            onItemClick = { playlist ->
//                navController.navigate("playlistDetail/${playlist.id}")
//            }
    ) { userLog: UserLog ->
        NotificationEntry(userLog)
    }
}
