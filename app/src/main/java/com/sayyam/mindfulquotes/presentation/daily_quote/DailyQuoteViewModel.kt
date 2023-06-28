package com.sayyam.mindfulquotes.presentation.daily_quote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayyam.mindfulquotes.domain.repository.QuoteRepository
import com.sayyam.mindfulquotes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import javax.inject.Inject

@HiltViewModel
class DailyQuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
): ViewModel(){

    var state by mutableStateOf(DailyQuoteState())

    init {
        getDailyQuote()
    }

    fun getDailyQuote() {
        viewModelScope.launch {
            repository.getDailyQuote()
                .collect{ result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let {
                                state = state.copy(quote = it)
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