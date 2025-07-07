package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.LikeButton
import com.example.myapplication1.ui.components.common.MenuButton
import com.example.myapplication1.ui.components.models.Song

@Composable
fun SongEntry(song: Song, isCharts: Boolean) {
    val openDialog = remember { mutableStateOf(false) }

//    if (openDialog.value) {
//        AlertDialogExample(
//            onDismissRequest = { openDialog.value = false },
//            onConfirmation = {
//                openDialog.value = false
//            },
//            dialogTitle = "이 노래가 마음에 드시나요?",
//            dialogText = "${song.title} - ${song.artist}\n를 플레이리스트에 추가합니다.",
//            icon = Icons.Default.Info
//        )
//    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 순위
            if (isCharts) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = (song.ranking ?: 0).toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            // 썸네일
            Box(modifier = Modifier.size(44.dp)) {
                Image(
                    painter = painterResource(id = song.thumbnailResId ?: R.drawable.song_dummy),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 제목 + 태그
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
                Row {
                    Text(
                        text = song.artist.joinToString(" & "),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                    Text(
                        text = "  •  ${song.length}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 좋아요 & 더보기 아이콘
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                LikeButton()
                Spacer(modifier = Modifier.height(8.dp))
                MenuButton()
            }
        }
    }
}

//@Composable
//fun AlertDialogExample(
//    onDismissRequest: () -> Unit,
//    onConfirmation: () -> Unit,
//    dialogTitle: String,
//    dialogText: String,
//    icon: ImageVector,
//) {
//    AlertDialog(
//        title = { Text(dialogTitle) },
//        text = { Text(dialogText) },
//        onDismissRequest = onDismissRequest,
//        confirmButton = {
//            TextButton(onClick = onConfirmation) { Text("Confirm") }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismissRequest) { Text("Dismiss") }
//        }
//    )
//}
