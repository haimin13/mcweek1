package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.data.model.User
import com.example.myapplication1.ui.components.popup.UserProfilePopup

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    users: List<User>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((User) -> Unit)? = null,
) {
    var selectedUser by remember { mutableStateOf<User?>(null) }

    GenericList(
        modifier = modifier,
        items = users,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = { user ->
            selectedUser = user
            onItemClick?.invoke(user)
        }
    ) { user ->
        UserEntry(user = user, isCharts = isCharts)
    }

    selectedUser?.let { user ->
        Dialog(onDismissRequest = { selectedUser = null }) {
            UserProfilePopup(userId = user.nickname, onDismiss = { selectedUser = null })
        }
    }
}