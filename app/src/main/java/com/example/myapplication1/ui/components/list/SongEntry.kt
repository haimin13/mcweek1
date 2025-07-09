package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.LikeButton
import com.example.myapplication1.ui.components.common.MenuButton
import com.example.myapplication1.data.model.Song


@Composable
fun SongEntry(
    modifier: Modifier = Modifier,
    song: Song,
    isCharts: Boolean,
    ranking: Int,
    artistNames: List<String> // ⬅️ 전달받는 리스트
) {
    val context = LocalContext.current
    val resId = remember(song.thumbnailId) {
        val resourceName = "song_${song.thumbnailId}"
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
    val painter = if (resId != 0) painterResource(id = resId) else painterResource(R.drawable.song_dummy)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
//        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 순위
            if (isCharts) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = ranking.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Box(modifier = Modifier.size(44.dp)) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 제목 + 태그
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
                Row {
                    Text(
                        text = artistNames.joinToString(" & "),
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
                MenuButton(listOf())
            }
        }
    }
}
