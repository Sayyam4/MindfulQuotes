package com.sayyam.mindfulquotes.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sayyam.mindfulquotes.presentation.error.ErrorCard
import com.sayyam.mindfulquotes.presentation.home.components.QuotesItem

@Composable
fun QuotesScreen(
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isRefreshing)
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.onEvent(QuotesEvent.Refresh)
    }) {
        if(state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            if(state.quotes.isEmpty()) {
                ErrorCard(message = "There are no quotes!!!")
            } else {
                Column(modifier = modifier.fillMaxSize()) {
                    LazyColumn(modifier = modifier.weight(1f)) {
                        items(state.quotes) {
                            QuotesItem(
                                quote = it,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}