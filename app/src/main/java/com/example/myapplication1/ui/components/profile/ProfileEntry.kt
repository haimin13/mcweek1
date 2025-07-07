package com.example.myapplication1.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.ui.components.models.Playlist
import com.example.myapplication1.ui.components.models.Song

@Composable
fun ProfileEntrySong(
    song: Song,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .clickable { onClick?.invoke() }
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = song.title,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = Color.Black,
        )
        Text(
            text = song.artist.joinToString(", "),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun ProfileEntryPlaylist(
    playlist: Playlist,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .clickable { onClick?.invoke() }
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = playlist.title,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            color = Color.Black,
        )
    }
}
@Composable
fun ProfileEntryMore() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = "more...",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

//
//
//@Composable
//fun ProfileEntry(
//    entry: Any?,  // 제네릭 대신 Any로 받고 내부에서 타입 분기
//    isMore: Boolean = false,
//    onClick: (() -> Unit)? = null
//) {
//    // 기본값
//    var title = "more..."
//    var subtitle: List<String>? = null
//
//    if (!isMore && entry != null) {
//        when (entry) {
//            is Song -> {
//                title = entry.title
//                subtitle = entry.artist // 또는 다른 필드로
//            }
//            is Playlist -> {
//                title = entry.title
//                // subtitle 없음
//            }
////            is User -> {
////                title = entry.name
////            }
//            // 추가 타입도 여기에 분기 가능
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .clickable(enabled = !isMore && onClick != null) { onClick?.invoke() }
//            .clip(RoundedCornerShape(4.dp))
//            .background(Color.White)
//            .padding(horizontal = 10.dp, vertical = 6.dp)
//    ) {
//        Text(
//            text = title,
//            fontWeight = FontWeight.Medium,
//            fontSize = 12.sp,
//            lineHeight = 12.sp,
//            color = if (isMore) Color.Gray else Color.Black,
//        )
//
//        // subtitle 있을 때만 보여줌
//        subtitle?.let {
//            Text(
//                text = subtitle.joinToString(" & "),
//                fontSize = 10.sp,
//                lineHeight = 10.sp,
//                color = Color.LightGray
//            )
//        }
//    }
//}