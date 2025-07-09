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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.common.LikeButton
import com.example.myapplication1.ui.components.common.MenuButton
import com.example.myapplication1.data.model.User

@Composable
fun UserEntry(
    user: User,
    isCharts: Boolean = false
) {
    // 리소스 이미지 불러오기
    val context = LocalContext.current
    val resId = remember(user.thumbnailId) {
        val resourceName = "user_${user.thumbnailId}"
        context.resources.getIdentifier(resourceName, "drawable", context.packageName)
    }
    val painter = if (resId != 0) resId else R.drawable.profile_default

    Card(
        modifier = Modifier.fillMaxWidth(),
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
//            if (isCharts) {
//                Spacer(modifier = Modifier.width(6.dp))
//                Text(
//                    text = (user.ranking ?: 0).toString(),
//                    style = MaterialTheme.typography.titleLarge
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//            }

            // 썸네일
            Box(modifier = Modifier.size(44.dp)) {
                Image(
                    painter = painterResource(painter),
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
                    text = user.nickname,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
//                Row {
//                    Text(
//                        text = user.genres.joinToString(" & "),
//                        style = MaterialTheme.typography.labelSmall,
//                        color = Color.Gray
//                    )
//                    Text(
//                        text = "  •  ${user.length}",
//                        style = MaterialTheme.typography.labelSmall,
//                        color = Color.Gray
//                    )
//                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 좋아요 & 더보기 아이콘
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                LikeButton()
                Spacer(modifier = Modifier.height(8.dp))
//                MenuButton(listOf())
            }
        }
    }
}