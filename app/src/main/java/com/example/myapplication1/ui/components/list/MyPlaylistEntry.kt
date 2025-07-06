package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.models.Playlist

@Composable
fun MyPlaylistEntry(playlist: Playlist, isCharts: Boolean) {
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialogExample(
            onDismissRequest = { openDialog.value = false },
            onConfirmation = {
                openDialog.value = false
            },
            dialogTitle = "이 노래가 마음에 드시나요?",
            dialogText = "${playlist.title} - ${playlist.author}\n를 플레이리스트에 추가합니다.",
            icon = Icons.Default.Info
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 순위
            if (isCharts) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = (playlist.ranking ?: 0).toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            // 썸네일
            Box(modifier = Modifier.size(52.dp)) {
                Image(
                    painter = painterResource(id = playlist.thumbnailResId ?: R.drawable.dummy),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 제목 + 태그
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = playlist.title,
                        style = MaterialTheme.typography.titleSmall.copy(lineHeight = 14.sp),
                        maxLines = 1
                    )
                    val visibilityIcon = when (playlist.visibility) {
                        0 -> " 🔓"
                        1 -> " 👥"
                        2 -> " 🔒"
                        else -> null
                    }
                    visibilityIcon?.let {
                        Text(
                            text = it,
                            lineHeight = 14.sp,
                            fontSize = 10.sp,
                            modifier = Modifier.alignByBaseline()
                        )
                    }
                }
                Text(
                    text = playlist.author,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    playlist.keywords.forEach {
                        Text(
                            text = "#$it",
                            fontSize = 10.sp,
                            lineHeight = 16.sp,
                            color = Color(0xFF5C6BC0),
                            modifier = Modifier
                                .background(Color(0xFFE8EAF6), RoundedCornerShape(6.dp))
                                .padding(horizontal = 4.dp, vertical = 0.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 좋아요 & 더보기 아이콘
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                val favoriteIcon = when (playlist.isLiked) {
                    true -> Icons.Default.FavoriteBorder
                    false -> Icons.Default.Favorite
                }
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Like",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { openDialog.value = true }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { openDialog.value = true }
                )
            }
        }
    }
}


@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        title = { Text(dialogTitle) },
        text = { Text(dialogText) },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmation) { Text("Confirm") }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) { Text("Dismiss") }
        }
    )
}
