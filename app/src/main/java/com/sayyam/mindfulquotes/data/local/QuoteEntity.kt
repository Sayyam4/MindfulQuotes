package com.sayyam.mindfulquotes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quoteentity")
data class QuoteEntity (
    @PrimaryKey
    val id: Int? = null,
    val quote: String,
    val author: String,
    var favorite: Boolean = false
)