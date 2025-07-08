package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.popup.UserProfilePopup

@Composable
fun GalleryEntry(
    contentId: Int = 0,
    contentType: String = "",
    contentName: String = "",
    thumbnailResId: Int = R.drawable.dummy,
    imageSize: Int = 100,
    textSize: Int = 13,
    showText: Boolean = false,
    showIcon: Boolean = true,
    isLiked: Boolean = false, // 좋아요 상태 추가
    onRemoveFriend: ((String) -> Unit)? = null,
    onTap: (() -> Unit)? = null, // 탭 동작을 인자로 받음
    onLongPress: (() -> Unit)? = null, // 롱프레스 동작도 인자로 받음
    onLikeToggle: ((String, Boolean) -> Unit)? = null // 좋아요 토글 콜백 수정
) {
    // isLiked 기본값은 database에 따르도록 변경
    var internalIsLiked by remember(isLiked) { mutableStateOf(isLiked) } // 외부 상태로 초기화
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
                            if (onTap != null) {
                                onTap()
                            } // 외부에서 전달받은 탭 동작 실행
                            else {
                                showTapDialog = true
                            } // 기본 동작 (기존 다이얼로그)
                        },
                        onLongPress = {
                            if (onLongPress != null) {
                                onLongPress()
                            } // 외부에서 전달받은 롱프레스 동작 실행
                            else {
                                showLongPressPopup = true
                            } // 기본 동작 (기존 팝업)
                        }
                    )
                }
        ) {
            // 사진
            Image(
                painter = painterResource(thumbnailResId),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            if (showIcon) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding((imageSize / 30).dp)
                        .clickable {
                            // LikeButton 클릭을 여기서 처리
                            internalIsLiked = !internalIsLiked
                            onLikeToggle?.invoke(contentName, internalIsLiked)
                        }
                ) {
                    // LikeButton을 수정하지 않고 아이콘만 직접 표시
                    Icon(
                        imageVector = if (internalIsLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (internalIsLiked) Color.Red else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            // 탭 다이얼로그
            if (showTapDialog && onTap == null) {
                Dialog(
                    onDismissRequest = { showTapDialog = false }
                ) {
                    UserProfilePopup (contentName, {showTapDialog = false})
                }
            }
            // GalleryEntry.kt에서 롱프레스 팝업 수정
            if (showLongPressPopup && onLongPress == null) {
                Popup(
                    alignment = Alignment.Center,
                    onDismissRequest = { showLongPressPopup = false }
                ) {
                    GalleryLongPress(
                        userId = contentName,
                        isCloseFriend = isLiked, // 현재 좋아요 상태를 친한 친구 여부로 사용
                        onRemoveFriend = { friendId ->
                            onRemoveFriend?.invoke(friendId)
                            showLongPressPopup = false
                        },
                        onToggleCloseFriend = { friendId, isClose ->
                            onLikeToggle?.invoke(friendId, isClose)
                            showLongPressPopup = false
                        },
                        onMuteFriend = { friendId ->
                            // 음소거 기능 구현
                            showLongPressPopup = false
                        },
                        onSeeProfile = { friendId ->
                            // 프로필 보기 기능 구현
                            showLongPressPopup = false
                        }
                    )
                }
            }

        }
        // 닉네임
        if (showText) {
            Text(
                text = contentName,
                fontSize = textSize.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}