package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.example.myapplication1.R

@Composable
fun GalleryEntry(
    contentId: Int = 0,
    contentType: String = "",
    contentName: String = "",
    imageSize: Int = 100,
    textSize: Int = 13,
    showText: Boolean = false,
    onTap: (() -> Unit)? = null, // 탭 동작을 인자로 받음
    onLongPress: (() -> Unit)? = null, // 롱프레스 동작도 인자로 받음
    onLikeToggle: ((Boolean) -> Unit)? = null // 좋아요 토글 동작
) {
    // isLiked 기본값은 database에 따르도록 변경
    var isLiked by remember { mutableStateOf(false) }
    var showTapDialog by remember { mutableStateOf(false) }
    var showLongPressPopup by remember { mutableStateOf(false) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(imageSize.dp)
    ) {
        Box(
            modifier = Modifier
                .size(imageSize.dp)
                .clip(RoundedCornerShape(10))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (onTap != null) { onTap() } // 외부에서 전달받은 탭 동작 실행
                            else { showTapDialog = true } // 기본 동작 (기존 다이얼로그)
                        },
                        onLongPress = {
                            if (onLongPress != null) { onLongPress() } // 외부에서 전달받은 롱프레스 동작 실행
                            else { showLongPressPopup = true } // 기본 동작 (기존 팝업)
                        }
                    )
                }
        ) {
            // 사진
            Image(
                painter = painterResource(R.drawable.dummy),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            // 좋아요 버튼
            IconButton(
                onClick = { isLiked = !isLiked },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(3.dp)
                    .size((imageSize / 5).dp)
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (isLiked) "좋아요 취소"  else "좋아요",
                    tint = if (isLiked) Color.Red else Color.LightGray
                )
            }
            // 탭 다이얼로그
            if (showTapDialog && onTap == null) {
                Dialog(
                    onDismissRequest = { showTapDialog = false }
                ) {
                    GalleryTap (contentName, {showTapDialog = false})
                }
            }
            // 홀드 팝업
            if (showLongPressPopup && onLongPress == null) {
                Popup(
                    alignment = Alignment.Center,
                    onDismissRequest = { showLongPressPopup = false }
                ) {
                    GalleryLongPress (contentName, {showLongPressPopup = false})
                }
            }
        }
        // 닉네임
        if (showText) {
            Text(
                text = contentName,
                fontSize = textSize.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}