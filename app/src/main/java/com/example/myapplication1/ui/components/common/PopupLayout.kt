package com.example.myapplication1.ui.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.components.gallery.GalleryEntry

@Composable
fun PopupLayout(
    title: String,
    thumbnailResId: Int = 0,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.width(400.dp),
        color = Color(0xfff3f3f3),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box {
            // Header: 닫기 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            // Body
            Column(
                modifier = Modifier
                    .padding(vertical = 36.dp, horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Thumbnail
                if (thumbnailResId != 0) {
                    GalleryEntry(
                        imageSize = 140,
                        thumbnailResId = thumbnailResId
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Custom content slot
                content()
            }
        }
    }
}
