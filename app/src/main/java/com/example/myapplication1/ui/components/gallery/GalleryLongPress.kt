package com.example.myapplication1.ui.components.gallery


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import com.example.myapplication1.ui.components.list.ContextMenuList
import com.example.myapplication1.ui.components.models_unused.ContextMenu

@Composable
fun GalleryLongPress(
    userId: Int,
    isCloseFriend: Boolean = false, // 친한 친구 여부 파라미터 추가
    onRemoveFriend: () -> Unit = {}, // 친구 제거 콜백
    onToggleCloseFriend: () -> Unit = {}, // 친한 친구 토글 콜백
    onSeeProfile: (Int) -> Unit = {} // 프로필 보기 콜백
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
                { onToggleCloseFriend() }
            )
        } else {
            ContextMenu(
                "remove close friend",
                Icons.Outlined.Favorite,
                { onToggleCloseFriend() }
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
            { onRemoveFriend() }
        )
    )
    ContextMenuList(menuItems = galleryMenuItems)
}