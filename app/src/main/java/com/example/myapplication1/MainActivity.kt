package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun PlaylistsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlists Screen")
    }
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
    Scaffold(modifier = modifier) { contentPadding ->
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
            modifier = Modifier.padding(contentPadding)
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