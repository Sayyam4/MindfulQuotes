package com.sayyam.mindfulquotes.presentation.daily_quote

import com.sayyam.mindfulquotes.domain.model.Quote

data class DailyQuoteState(
    val quote: List<Quote> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)