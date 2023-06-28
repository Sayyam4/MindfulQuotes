package com.sayyam.mindfulquotes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesList(quotes: List<QuoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("DELETE FROM quoteentity")
    suspend fun deleteQuoteList()

    @Query("SELECT * FROM quoteentity")
    suspend fun getStoredQuotesList(): List<QuoteEntity>

    @Query("SELECT * FROM quoteentity WHERE favorite = 1")
    suspend fun getFavoriteQuotes(): List<QuoteEntity>

}