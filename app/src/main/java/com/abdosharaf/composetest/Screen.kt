package com.abdosharaf.composetest

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailsScreen: Screen("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}