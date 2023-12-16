package com.abdosharaf.composetest

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppBar(onIconClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Navigation Drawer")
        },
        backgroundColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIcon = {
            IconButton(onClick = onIconClicked) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open the drawer"
                )
            }
        }
    )
}