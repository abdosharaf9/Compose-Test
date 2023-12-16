package com.abdosharaf.composetest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(appKiller = {
                finish()
            })
        }
    }
}

@Preview
@Composable
fun Test() {
    HomeScreen(appKiller = {
        println("App is closed")
    })
}

@Composable
fun HomeScreen(appKiller: () -> Unit) {
    val drawerList = listOf(
        MenuItem(
            title = "Home",
            icon = Icons.Default.Home,
            contentDescription = "Got to Home",
            id = "home"
        ),
        MenuItem(
            title = "Settings",
            icon = Icons.Default.Settings,
            contentDescription = "Got to Settings",
            id = "settings"
        ),
        MenuItem(
            title = "Profile",
            icon = Icons.Default.AccountCircle,
            contentDescription = "Got to Profile",
            id = "profile"
        ),
        MenuItem(
            title = "Info",
            icon = Icons.Default.Info,
            contentDescription = "Got to Info",
            id = "info"
        ),
        MenuItem(
            title = "Exit",
            icon = Icons.Default.ExitToApp,
            contentDescription = "Exit the app",
            id = "exit"
        )
    )
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(onIconClicked = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(items = drawerList, onItemClicked = {
                if (it.id == "exit") {
                    appKiller()
                } else {
                    Toast.makeText(context, it.contentDescription, Toast.LENGTH_SHORT).show()
                }
            })
        }
    ) {
        Box(modifier = Modifier.padding(it))
    }
}