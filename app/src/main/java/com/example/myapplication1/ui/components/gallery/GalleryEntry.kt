package com.example.myapplication1.ui.components.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.example.myapplication1.R

@Composable
fun GalleryEntry(
    userId: String,
    imageSize: Int = 100,
    textSize: Int = 13,
    tapDialog: @Composable (String, () -> Unit) -> Unit,
    longPressDialog: @Composable (String, () -> Unit) -> Unit,
    modifier: Modifier = Modifier
) {
    // isLiked 기본값은 database에 따르도록 변경
    var isLiked by remember { mutableStateOf(false) }
    var showTapDialog by remember { mutableStateOf(false) }
    var showLongPressDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(imageSize.dp)
                .clip(RoundedCornerShape(10))
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { showTapDialog = true },
                        onLongPress = { showLongPressDialog = true }
                    )
                }
        ) {
            Image(
                painter = painterResource(R.drawable.dummy),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
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
            if (showTapDialog) {
                Dialog(onDismissRequest = { showTapDialog = false }) {
                    tapDialog(userId, {showTapDialog = false})
                }
            }
            if (showLongPressDialog) {
                Dialog(onDismissRequest = { showLongPressDialog = false }) {
                    longPressDialog(userId, {showLongPressDialog = false})
                }
            }
        }

        Text(
            text = userId,
            fontSize = textSize.sp
        )
    }
}