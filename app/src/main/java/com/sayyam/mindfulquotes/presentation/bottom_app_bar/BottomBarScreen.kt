package com.sayyam.mindfulquotes.presentation.bottom_app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiObjects
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object DailyQuote: BottomBarScreen(
        route = "daily_quote",
        title = "Daily Quote",
        icon = Icons.Default.EmojiObjects
    )
    object Favorites: BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Filled.Favorite
    )
}