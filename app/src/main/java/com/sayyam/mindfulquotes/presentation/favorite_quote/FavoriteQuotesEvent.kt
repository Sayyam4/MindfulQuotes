package com.sayyam.mindfulquotes.presentation.favorite_quote

import com.sayyam.mindfulquotes.data.local.QuoteEntity

sealed class FavoriteQuotesEvent {
    data class OnLikeChange(val quoteEntity: QuoteEntity): FavoriteQuotesEvent()
}