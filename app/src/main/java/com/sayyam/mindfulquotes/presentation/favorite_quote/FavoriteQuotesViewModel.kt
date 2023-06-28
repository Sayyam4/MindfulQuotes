package com.sayyam.mindfulquotes.presentation.favorite_quote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayyam.mindfulquotes.domain.repository.QuoteRepository
import com.sayyam.mindfulquotes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteQuotesViewModel @Inject constructor(
    private val repository: QuoteRepository
): ViewModel() {
    var state by mutableStateOf(FavoriteQuotesState())
    init {
        getFavoriteQuotes()
    }

    fun onEvent(event: FavoriteQuotesEvent) {
        when(event) {
            is FavoriteQuotesEvent.OnLikeChange -> {
                viewModelScope.launch {
                    repository.insertQuote(
                        event.quoteEntity.copy(
                            favorite = !event.quoteEntity.favorite
                        )
                    )
                    state = state.withUpdatedQuoteLike(event.quoteEntity)
                }
            }
        }
    }

    fun getFavoriteQuotes() {
        viewModelScope.launch {
            repository.getFavoriteQuotes()
                .collect{ result->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { quotes ->
                                state = state.copy(quotes = quotes)
                            }
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                        else -> Unit
                    }
                }
        }
    }
}