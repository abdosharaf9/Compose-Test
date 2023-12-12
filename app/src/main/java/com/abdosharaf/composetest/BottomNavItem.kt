package com.abdosharaf.composetest

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val badgeCount: Int,
    val icon: ImageVector
)
