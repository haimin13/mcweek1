package com.example.myapplication1.ui.components.customLazy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.components.gallery.GalleryEntry
import com.example.myapplication1.ui.components.gallery.GalleryLongPress
import com.example.myapplication1.ui.components.gallery.GalleryTap

@Composable
fun CustomLazyRow(
    modifier: Modifier = Modifier,
    itemList: List<String>,
    itemContent: @Composable (
        String,
        Int,
        Int,
        @Composable (String, () -> Unit) -> Unit,
        @Composable (String, () -> Unit) -> Unit
    ) -> Unit,
    imageSize: Int = 100,
    textSize: Int = 13,
    tapRef: @Composable (String, () -> Unit) -> Unit,
    longPressRef: @Composable (String, () -> Unit) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(itemList) { id ->
            itemContent(id, imageSize, textSize, tapRef, longPressRef)
        }
    }
}