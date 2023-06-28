package com.sayyam.mindfulquotes.data.mapper

import com.sayyam.mindfulquotes.data.local.QuoteEntity
import com.sayyam.mindfulquotes.data.remote.dto.DailyQuoteDto
import com.sayyam.mindfulquotes.data.remote.dto.QuoteDto
import com.sayyam.mindfulquotes.domain.model.Quote

fun Quote.toQuoteEntity(): QuoteEntity {
    return QuoteEntity(
        quote = quote,
        author = author
    )
}

fun QuoteDto.toQuote(): Quote {
    return Quote(
        quote = q,
        author = a
    )
}

fun DailyQuoteDto.toQuote(): Quote {
    return Quote(
        quote = q,
        author = a
    )
}