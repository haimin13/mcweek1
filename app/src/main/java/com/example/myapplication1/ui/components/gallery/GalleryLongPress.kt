package com.example.myapplication1.ui.components.gallery


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.list.ContextMenuList
import com.example.myapplication1.ui.components.models.ContextMenu

@Composable
fun GalleryLongPress(
    userId: String,
    isCloseFriend: Boolean = false, // 친한 친구 여부 파라미터 추가
    onRemoveFriend: (String) -> Unit = {}, // 친구 제거 콜백
    onToggleCloseFriend: (String, Boolean) -> Unit = { _, _ -> }, // 친한 친구 토글 콜백
    onMuteFriend: (String) -> Unit = {}, // 음소거 콜백
    onSeeProfile: (String) -> Unit = {} // 프로필 보기 콜백
) {
    val galleryMenuItems = listOf(
        ContextMenu(
            "see profile",
            Icons.Outlined.AccountCircle,
            { onSeeProfile(userId) }
        ),

        // 친한 친구 여부에 따라 다른 메뉴 표시
        if (!isCloseFriend) {
            ContextMenu(
                "add to close friend",
                Icons.Filled.Favorite,
                { onToggleCloseFriend(userId, true) }
            )
        } else {
            ContextMenu(
                "remove close friend",
                Icons.Outlined.Favorite,
                { onToggleCloseFriend(userId, false) }
            )
        },
/*

        ContextMenu(
            "mute friend",
            ImageVector.vectorResource(R.drawable.outline_notifications_off_24),
            { onMuteFriend(userId) }
        ),
*/

        ContextMenu(
            "remove friend",
            Icons.Outlined.Delete,
            { onRemoveFriend(userId) }
        )
    )
    ContextMenuList(menuItems = galleryMenuItems)
}