package com.sayyam.mindfulquotes.di

import android.app.Application
import androidx.room.Room
import com.sayyam.mindfulquotes.data.local.QuoteDatabase
import com.sayyam.mindfulquotes.data.remote.QuoteApi
import com.sayyam.mindfulquotes.data.repository.QuoteRepositoryImpl
import com.sayyam.mindfulquotes.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuoteModule {

    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder().baseUrl(QuoteApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Application): QuoteDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = QuoteDatabase::class.java,
            name = "quotes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(db: QuoteDatabase, api: QuoteApi): QuoteRepository {
        return QuoteRepositoryImpl(api = api, db = db)
    }

}