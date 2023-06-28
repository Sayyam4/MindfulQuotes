package com.sayyam.mindfulquotes.data.remote

import com.sayyam.mindfulquotes.data.remote.dto.DailyQuoteDto
import com.sayyam.mindfulquotes.data.remote.dto.QuoteDto
import retrofit2.http.GET

interface QuoteApi {

    @GET("/api/quotes")
    suspend fun getQuotesList(): List<QuoteDto>

    @GET("/api/today")
    suspend fun getDailyQuote(): List<DailyQuoteDto>

    companion object {
        const val BASE_URL = "https://zenquotes.io"
    }
}