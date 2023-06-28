package com.sayyam.mindfulquotes.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sayyam.mindfulquotes.presentation.bottom_app_bar.BottomBar
import com.sayyam.mindfulquotes.presentation.bottom_app_bar.BottomNavGraph
import com.sayyam.mindfulquotes.presentation.top_app_bar.QuotesTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesApp() {
    val navController = rememberNavController()
    Scaffold (
        topBar = { QuotesTopAppBar() },
        bottomBar = { BottomBar(navController = navController) },
    ) {
        BottomNavGraph(navController = navController, modifier = Modifier.padding(it))
    }
}