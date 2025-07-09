package com.example.myapplication1.ui.components.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication1.data.model.Artist
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.ui.components.popup.ArtistDetailPopup

@Composable
fun ArtistList(
    modifier: Modifier = Modifier,
    artists: List<Artist>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Artist) -> Unit)? = null,
) {
    var selectedArtist by remember { mutableStateOf<Artist?>(null) }

    GenericList(
        modifier = modifier,
        items = artists,
        verticalSpacing = verticalSpacing.dp,
        onItemClick = { artist ->
            selectedArtist = artist
            onItemClick?.invoke(artist)
        }
    ) { artist ->
        ArtistEntry(artist = artist, isCharts = isCharts)
    }

    selectedArtist?.let { artist ->
        Dialog(onDismissRequest = { selectedArtist = null }) {
            ArtistDetailPopup(artist = artist, onDismiss = { selectedArtist = null })
        }
    }
}
//
//val dummyArtistList = listOf(
//    Artist(
//        id = 1,
//        title = "IU",
//        thumbnailResId = null,
//        genres = listOf("K-Pop", "Ballad"),
//        isLiked = true,
//    ),
//    Artist(
//        id = 2,
//        title = "BTS",
//        thumbnailResId = null,
//        genres = listOf("K-Pop", "Hip-Hop"),
//        isLiked = false,
//    ),
//    Artist(
//        id = 3,
//        title = "BLACKPINK",
//        thumbnailResId = null,
//        genres = listOf("K-Pop"),
//        isLiked = true,
//    ),
//    Artist(
//        id = 4,
//        title = "Zico",
//        thumbnailResId = null,
//        genres = listOf("Hip-Hop", "R&B"),
//        isLiked = false,
//    ),
//    Artist(
//        id = 5,
//        title = "AKMU",
//        thumbnailResId = null,
//        genres = listOf("Indie", "Folk"),
//        isLiked = true,
//    ),
//    Artist(
//        id = 6,
//        title = "Taeyeon",
//        thumbnailResId = null,
//        genres = listOf("Ballad", "Pop"),
//        isLiked = false,
//    )
//)

