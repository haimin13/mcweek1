package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.KeywordTag
import com.example.myapplication1.ui.components.common.LikeButton
import com.example.myapplication1.ui.components.common.MenuButton
import com.example.myapplication1.ui.components.gallery.GalleryLongPress
import com.example.myapplication1.data.model.Playlist

@Composable
fun PlaylistEntry(playlist: Playlist, isCharts: Boolean) {
//    // 리소스 이미지 불러오기
//    val context = LocalContext.current
//    val resId = remember(song.thumbnailId) {
//        val resourceName = "song_${song.thumbnailId}"
//        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
//    }
//    val painter = if (resId != 0) resId else R.drawable.song_dummy

    Card(
        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

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
                    //TODO:
//                    painter = painterResource(id = playlist.thumbnailId ?: R.drawable.dummy),
                    painter = painterResource(R.drawable.dummy),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

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
                    text = playlist.author.toString(), //Todo: playlist.author (Int)로 이름 가져오기
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    playlist.keywords?.forEach { keyword ->
                        KeywordTag(text = keyword)
                    }
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
