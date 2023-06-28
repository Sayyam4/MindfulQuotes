package com.sayyam.mindfulquotes.presentation.favorite_quote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sayyam.mindfulquotes.presentation.error.ErrorCard
import com.sayyam.mindfulquotes.presentation.favorite_quote.components.FavoriteQuotesItem

@Composable
fun FavoriteQuotesScreen(
    viewModel: FavoriteQuotesViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box {
        if(state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if(state.quotes.isEmpty()) {
                ErrorCard(message = "There are no favorite quotes!!!")
            } else {
                LazyColumn(modifier = Modifier) {
                    items(state.quotes) {
                        FavoriteQuotesItem(
                            quote = it,
                            onEvent = viewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}