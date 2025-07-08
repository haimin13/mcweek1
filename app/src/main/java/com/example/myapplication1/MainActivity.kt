package com.example.myapplication1

import com.example.myapplication1.ui.screens.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.ui.screens.PlaylistsTabMain
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home: BottomNavItem("home", "home", Icons.Outlined.Home)
    object Playlists: BottomNavItem("playlists", "playlists", Icons.Outlined.PlayArrow)
    object Friends: BottomNavItem("friends", "friends", Icons.Outlined.Favorite)
    object My: BottomNavItem("my", "my", Icons.Outlined.AccountCircle)
}


@Composable
fun MainScreen(modifier: Modifier) {
    val navController = rememberNavController()

    var myId by remember { mutableStateOf(0)}

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Playlists,
        BottomNavItem.Friends,
        BottomNavItem.My
    )
    // 탭별 시작 destination 매핑
    val tabStartDestinations = mapOf(
        "home" to "homeMain",
        "playlists" to "playlistsMain",
        "friends" to "friendsMain",
        "my" to "myMain"
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    val startDestination = tabStartDestinations[item.route] ?: item.route
                    val isInCurrentTab = isRouteInTab(currentRoute, item.route)

                    NavigationBarItem(
                        selected = isInCurrentTab,
                        onClick = {
                            if (isInCurrentTab) {
                                // 같은 탭: 시작점으로 이동
                                navController.popBackStack(startDestination, false)
                            } else {
                                val currentTabRoute = getCurrentTabRoute(currentRoute)
                                currentTabRoute?.let { tabRoute ->
                                    val currentTabStartDestination = tabStartDestinations[tabRoute]
                                    if (currentTabStartDestination != null && currentRoute != currentTabStartDestination) {
                                        navController.popBackStack(currentTabStartDestination, false)
                                    }
                                }
                                // 다른 탭: 해당 탭으로 이동
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route, //Todo: 개발 후에 Home으로 바꾸기
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            navigation(startDestination = "homeMain", route = BottomNavItem.Home.route) {
                composable("homeMain") { HomeTabMain(navController = navController) }
            }
            navigation(startDestination = "playlistsMain", route = BottomNavItem.Playlists.route) {
                composable("playlistsMain") { PlaylistsTabMain(modifier = Modifier, navController = navController)}
            }
            navigation(startDestination = "friendsMain", route = BottomNavItem.Friends.route) {
                composable("friendsMain") {
                    FriendsTabMain(
                        onNotificationClick = { navController.navigate("friends/notifications") },
                        myId = myId
                    )
                }
                composable("friends/notifications") {
                    NotificationPage (
                        onBackClick = { navController.popBackStack() },
                        myId = myId
                    )
                }
            }
            navigation(startDestination = "myMain", route = BottomNavItem.My.route) {
                composable("myMain") { MyTabMain(
                    myId = myId,
                    onMyIdChange = { newId -> myId = newId } // myId 변경 콜백
                ) }
            }
        }
    }
}

private fun isRouteInTab(currentRoute: String?, tabRoute: String): Boolean {
    return when (tabRoute) {
        "home" -> currentRoute == "homeMain"
        "playlists" -> currentRoute == "playlistsMain"
        "friends" -> currentRoute in listOf("friendsMain", "friends/notifications")
        "my" -> currentRoute == "myMain"
        else -> false
    }
}

private fun getCurrentTabRoute(currentRoute: String?): String? {
    return when (currentRoute) {
        "homeMain" -> "home"
        "playlistsMain" -> "playlists"
        "friendsMain", "friends/notifications" -> "friends"
        "myMain" -> "my"
        else -> null
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    MainScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    )
                }
            }
        }
    }
}