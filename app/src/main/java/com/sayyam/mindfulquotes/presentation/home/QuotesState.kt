package com.sayyam.mindfulquotes.presentation.home

import com.sayyam.mindfulquotes.data.local.QuoteEntity

data class QuotesState (
    val quotes: List<QuoteEntity> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String = ""
) {
    fun withUpdatedQuoteLike(quoteEntity: QuoteEntity, newLikeValue: Boolean): QuotesState {
        val updatedQuotesList = quotes.map {
            if (it.id == quoteEntity.id) {
                it.copy(favorite = newLikeValue)
            } else {
                it
            }
        }
        return copy(quotes = updatedQuotesList)
    }
}