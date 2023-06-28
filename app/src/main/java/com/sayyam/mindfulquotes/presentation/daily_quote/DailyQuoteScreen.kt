package com.sayyam.mindfulquotes.presentation.daily_quote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sayyam.mindfulquotes.presentation.daily_quote.components.QuoteItem
import com.sayyam.mindfulquotes.presentation.error.ErrorCard

@Composable
fun DailyQuoteScreen(
    viewModel: DailyQuoteViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if (state.quote.isNotEmpty()) {
                val quote = state.quote[0]
                QuoteItem(quote.quote, quote.author)
            } else {
                ErrorCard(message = "Daily Quote is Not Available!!!")
            }
        }
    }
}