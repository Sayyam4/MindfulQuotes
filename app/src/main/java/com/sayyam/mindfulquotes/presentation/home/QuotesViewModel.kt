package com.sayyam.mindfulquotes.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayyam.mindfulquotes.domain.repository.QuoteRepository
import com.sayyam.mindfulquotes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val repository: QuoteRepository
): ViewModel() {
    var state by mutableStateOf(QuotesState())

    init {
        getQuotesList()
    }

    fun onEvent(event: QuotesEvent) {
        when (event) {
            is QuotesEvent.Refresh -> {
                getQuotesList(true)
            }
            is QuotesEvent.OnLikeChange -> {
                viewModelScope.launch {
                    repository.insertQuote(
                        event.quoteEntity.copy(
                            favorite = !event.quoteEntity.favorite
                        )
                    )
                    state = state.withUpdatedQuoteLike(event.quoteEntity, !event.quoteEntity.favorite)
                }
            }
        }
    }

    private fun getQuotesList(fetchFromRemote:Boolean = false) {
        viewModelScope.launch {
            repository.getQuotesList(fetchFromRemote)
                .collect{result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { quotes ->
                                state = state.copy(quotes = quotes)
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(error = result.message.toString())
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}