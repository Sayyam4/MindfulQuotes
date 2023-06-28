package com.sayyam.mindfulquotes.presentation.home

import com.sayyam.mindfulquotes.data.local.QuoteEntity

sealed class QuotesEvent  {
    object Refresh: QuotesEvent()
    data class OnLikeChange(val quoteEntity: QuoteEntity): QuotesEvent()
}