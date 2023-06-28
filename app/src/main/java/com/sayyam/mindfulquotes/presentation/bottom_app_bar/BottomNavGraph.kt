package com.sayyam.mindfulquotes.presentation.bottom_app_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sayyam.mindfulquotes.presentation.daily_quote.DailyQuoteScreen
import com.sayyam.mindfulquotes.presentation.favorite_quote.FavoriteQuotesScreen
import com.sayyam.mindfulquotes.presentation.home.QuotesScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            QuotesScreen()
        }
        composable(route = BottomBarScreen.DailyQuote.route) {
            DailyQuoteScreen()
        }
        composable(route = BottomBarScreen.Favorites.route) {
            FavoriteQuotesScreen()
        }
    }
}