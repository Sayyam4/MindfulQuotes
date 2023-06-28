package com.sayyam.mindfulquotes.presentation.favorite_quote

import com.sayyam.mindfulquotes.data.local.QuoteEntity

data class FavoriteQuotesState(
    val quotes: List<QuoteEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
) {
    fun withUpdatedQuoteLike(quoteEntity: QuoteEntity): FavoriteQuotesState {
        val updatedQuotesList = quotes.filterNot { it.id == quoteEntity.id }
        return copy(quotes = updatedQuotesList)
    }
}
