package com.sayyam.mindfulquotes.domain.repository

import com.sayyam.mindfulquotes.data.local.QuoteEntity
import com.sayyam.mindfulquotes.domain.model.Quote
import com.sayyam.mindfulquotes.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    suspend fun getQuotesList(fetchFromRemote:Boolean): Flow<Resource<List<QuoteEntity>>>

    suspend fun insertQuote(quote: QuoteEntity)

    fun getDailyQuote(): Flow<Resource<List<Quote>>>

    suspend fun getFavoriteQuotes(): Flow<Resource<List<QuoteEntity>>>

}