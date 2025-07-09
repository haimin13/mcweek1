package com.example.myapplication1.ui.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.ui.components.popup.SongDetailPopup
import com.example.myapplication1.data.model.Song
import com.example.myapplication1.ui.remote.ArtistViewModel

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    verticalSpacing: Int = 8,
    isCharts: Boolean = false,
    onItemClick: ((Song) -> Unit)? = null,
    artistViewModel: ArtistViewModel = viewModel()
) {
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    val allArtistIds = remember(songs) {
        songs.flatMap { it.artist }.distinct()
    }

    LaunchedEffect(allArtistIds) {
        artistViewModel.loadArtistsByIdList(allArtistIds)
    }

    val artistList by artistViewModel.artists.observeAsState(emptyList())
    val artistMap = artistList.associateBy { it.id }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp)
    ) {
        itemsIndexed(songs) { index, song ->
            val artistNames = song.artist.mapNotNull { artistMap[it]?.nickname }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedSong = song
                        onItemClick?.invoke(song)
                    }
            ) {
                SongEntry(
                    song = song,
                    isCharts = isCharts,
                    ranking = index + 1,
                    artistNames = artistNames // ⬅️ 직접 전달
                )
            }
        }
    }

    selectedSong?.let { song ->
        val selectedArtists = song.artist.mapNotNull { artistMap[it] }

        Dialog(onDismissRequest = { selectedSong = null }) {
            SongDetailPopup(
                song = song,
                artists = selectedArtists,
                onDismiss = { selectedSong = null }
            )
        }
    }
}
