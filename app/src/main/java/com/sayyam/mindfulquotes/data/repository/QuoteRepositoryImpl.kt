package com.sayyam.mindfulquotes.data.repository

import com.sayyam.mindfulquotes.data.local.QuoteDatabase
import com.sayyam.mindfulquotes.data.local.QuoteEntity
import com.sayyam.mindfulquotes.data.mapper.toQuote
import com.sayyam.mindfulquotes.data.mapper.toQuoteEntity
import com.sayyam.mindfulquotes.data.remote.QuoteApi
import com.sayyam.mindfulquotes.domain.model.Quote
import com.sayyam.mindfulquotes.domain.repository.QuoteRepository
import com.sayyam.mindfulquotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteRepositoryImpl @Inject constructor(
    private val api: QuoteApi,
    db: QuoteDatabase
): QuoteRepository {
    private val dao = db.dao

    override suspend fun getQuotesList(fetchFromRemote: Boolean): Flow<Resource<List<QuoteEntity>>> {
        return flow {
            emit(Resource.Loading(true))
            val localQuoteList = dao.getStoredQuotesList()
            emit(Resource.Success(data = localQuoteList))

            val isDbEmpty = localQuoteList.isEmpty()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteQuotesList = try {
                val response = api.getQuotesList()
                response.map { it.toQuote() }
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            }

            remoteQuotesList?.let { quotes ->
                dao.deleteQuoteList()
                dao.insertQuotesList(quotes.map { it.toQuoteEntity() })
                emit(Resource.Success(dao.getStoredQuotesList()))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun insertQuote(quote: QuoteEntity) {
        dao.insertQuote(quote)
    }

    override fun getDailyQuote(): Flow<Resource<List<Quote>>> {
        return flow {
            emit(Resource.Loading(true))
            val dailyQuote = try {
                val response = api.getDailyQuote()
                response.map { it.toQuote() }
            } catch (e: IOException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Error = ${e.message}"))
                null
            }
            dailyQuote?.let {
                emit(Resource.Success(it))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getFavoriteQuotes(): Flow<Resource<List<QuoteEntity>>> {
        return flow {
            emit(Resource.Loading(true))
            emit(Resource.Success(dao.getFavoriteQuotes()))
            emit(Resource.Loading(false))
        }
    }
}