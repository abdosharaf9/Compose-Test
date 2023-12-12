package com.abdosharaf.composetest

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun Test() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "main_screen",
            badgeCount = 0,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "Chat",
            route = "chat_screen",
            badgeCount = 1000000000,
            icon = Icons.Default.Email
        ),
        BottomNavItem(
            name = "Settings",
            route = "settings_screen",
            badgeCount = 0,
            icon = Icons.Default.Settings
        )
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavWithBadge(
                items = bottomNavItems,
                navController = navController,
                onItemClicked = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Navigation(navHostController = navController)
        }
    }
}