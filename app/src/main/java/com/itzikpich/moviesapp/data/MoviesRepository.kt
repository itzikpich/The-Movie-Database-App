package com.itzikpich.moviesapp.data


import android.util.Log
import com.itzikpich.moviesapp.data.local.LocalDataSource
import com.itzikpich.moviesapp.data.local.LocalDataSourceImpl
import com.itzikpich.moviesapp.data.remote.RemoteDataSource
import com.itzikpich.moviesapp.data.remote.RemoteDataSourceImpl
import com.itzikpich.moviesapp.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MoviesRepository remains in ApplicationComponent
 * because it's scoped to the ApplicationComponent.
 * If the project grows, you want to share the same instance across different features.
 *
 * Because MoviesRepository is part of ApplicationComponent,
 * its dependencies (i.e. LocalDataSource and RemoteDataSource) need to be in this component too
 * in order to be able to provide instances of Repository
 * */

@Singleton
class MoviesRepository @Inject constructor(
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) : LocalDataSource by localDataSourceImpl {

//    fun localCategory(id: Int) = localDataSourceImpl.loadCategory(id)

    suspend fun getMovieResultFromRemote(path: String, page: Int) : Flow<Result<MovieResult>> {
        return flow {
            Log.d("MoviesRepository" , Thread.currentThread().name)
            try {
                val response = remoteDataSourceImpl.getMoviesResultFromNetwork(path, page)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        emit(Result.Success(it))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
        }
    }

    //region MovieDetails

    suspend fun getMovieDetailsFromRemote(movieId: Int) : Flow<Result<MovieDetailsItem?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSourceImpl.getMovieDetailsFromNetwork(movieId)
                if (response.isSuccessful) {
                    emit(Result.Success(response.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }


    // endregion

    //region videoItem

    suspend fun getVideoItemFromRemote(movieId: Int) : Flow<Result<VideoItem?>> {
        Log.d("MoviesRepository" , Thread.currentThread().name)
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSourceImpl.getVideoItemFromNetwork(movieId)
                if (response.isSuccessful) {
                    emit(Result.Success(response.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }


    // endregion

    //region CreditItem

    suspend fun getCreditsItemFromRemote(movieId: Int) : Flow<Result<CreditsItem?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSourceImpl.getCreditItemFromNetwork(movieId)
                if (response.isSuccessful) {
                    emit(Result.Success(response.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    // endregion
}