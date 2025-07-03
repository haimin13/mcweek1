package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.myapplication1.ui.theme.MyApplication1Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class Destinations(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    PLAYLISTS("playlists", "Playlists", Icons.Default.PlayArrow, "Playlists"),
    GALLERY("gallery", "Gallery", Icons.Default.Favorite, "Gallery"),
    ANYTHING("anything", "Anything", Icons.Default.MoreVert, "Anything")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Tabs(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    )
                }
            }
        }
    }
}

data class Song(
    val title: String,
    val artist: String,
    val keywords: List<String>
)

val playList = listOf(
    Song("Summer", "The volunteers", listOf("여름", "감성", "밴드")),
    Song("Hype Boy", "NewJeans", listOf("청량", "중독성", "댄스")),
    Song("봄날", "BTS", listOf("감성", "겨울", "그리움"))
)

@Composable
fun PlaylistsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
//        Text("Playlists Screen")
        PlaylistView(playList)
    }
}

@Composable
fun PlaylistView(playlist: List<Song>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(playlist) { item ->
            PlaylistItem(song = item)
        }
    }
}

@Composable
fun PlaylistItem(song: Song) {
    val openDialog = remember { mutableStateOf(false) }
    val dialogType = true

    if (openDialog.value && dialogType) {
        AlertDialogExample(
            onDismissRequest = { openDialog.value = false },
            onConfirmation = {
                openDialog.value = false
                println("Confirmation registered for ${song.title}")
            },
            dialogTitle = "이 노래가 마음에 드시나요?",
            dialogText = "${song.title} - ${song.artist} \n를 플레이리스트에 추가합니다.",
            icon = Icons.Default.Info
        )
    }

    if (openDialog.value && !dialogType) {
        MinimalDialog(
            onDismissRequest = { openDialog.value = false }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openDialog.value = true },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(song.title, style = MaterialTheme.typography.titleMedium)
                Text(song.artist, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                song.keywords.forEach {keyword ->
                    Box(
                        modifier = Modifier
                        .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("#$keyword", fontSize = 12.sp, color = Color(0xFF00796B))
                    }
                }
            }
        }
    }
}

// minimal dialog
@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

// alert dialog
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
//        icon = {
//            Icon(icon, contentDescription = "Example Icon")
//        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}


@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlists Screen")
    }
}

@Composable
fun AnythingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Anything Screen")
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destinations,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destinations.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destinations.PLAYLISTS -> PlaylistsScreen()
                    Destinations.GALLERY -> GalleryScreen()
                    Destinations.ANYTHING -> AnythingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tabs(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destinations.PLAYLISTS
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Column(modifier = modifier) {
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
        ) {
            Destinations.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        AppNavHost(navController, startDestination)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication1Theme {
        Tabs()
    }
}