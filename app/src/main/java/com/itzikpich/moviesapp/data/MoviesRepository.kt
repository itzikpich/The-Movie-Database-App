package com.itzikpich.moviesapp.data


import com.itzikpich.moviesapp.data.local.LocalDataSource
import com.itzikpich.moviesapp.data.remote.RemoteDataSource
import com.itzikpich.moviesapp.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
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
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    fun localCategory(id: Int) = localDataSource.loadCategory(id)

    suspend fun addCategoryToLocal(categoryItem: CategoryItem) {
        withContext(Dispatchers.IO) {
            localDataSource.addCategory(categoryItem)
        }
    }

    suspend fun getMovieResultFromRemote(path: String, page: Int) : Flow<Result<MovieResult?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSource.getMoviesResultFromNetwork(path, page)
                val body = response.body()
                if (response.isSuccessful) {
                    body?.let { it ->
                        it?.let {
                            emit(Result.Success(it))
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }

    //region MovieDetails

    fun localMovieDetails(id: Int) = localDataSource.loadMovieDetailsItem(id)

    suspend fun addMovieDetailsToLocal(movieDetailsItem: MovieDetailsItem) {
        withContext(Dispatchers.IO) {
            localDataSource.addMovieDetailsItem(movieDetailsItem)
        }
    }

    suspend fun getMovieDetailsFromRemote(movieId: Int) : Flow<Result<MovieDetailsItem?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSource.getMovieDetailsFromNetwork(movieId)
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

    fun localVideoDetails(id: Int) = localDataSource.loadVideoItem(id)

    suspend fun addVideoItemToLocal(videoItem: VideoItem) {
        withContext(Dispatchers.IO) {
            localDataSource.addVideoItem(videoItem)
        }
    }

    suspend fun getVideoItemFromRemote(movieId: Int) : Flow<Result<VideoItem?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSource.getVideoItemFromNetwork(movieId)
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

    fun localCreditsItem(id: Int) = localDataSource.loadCreditsItem(id)

    suspend fun addCreditsItemToLocal(creditsItem: CreditsItem) {
        withContext(Dispatchers.IO) {
            localDataSource.addCreditsItem(creditsItem)
        }
    }

    suspend fun getCreditsItemFromRemote(movieId: Int) : Flow<Result<CreditsItem?>> {
        return flow {
            emit(Result.Loading)
            try {
                val response = remoteDataSource.getCreditItemFromNetwork(movieId)
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