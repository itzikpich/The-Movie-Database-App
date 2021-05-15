package com.itzikpich.moviesapp.data.remote

import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.models.VideoItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: RetrofitService
) {

    suspend fun getMoviesResultFromNetwork(categoryName: String, page: Int) = apiService.getMovieResult(categoryName, page)

    suspend fun getMovieDetailsFromNetwork(movieId: Int) = apiService.getMovieDetails(movieId)

    suspend fun getCreditItemFromNetwork(movieId: Int) = apiService.getMovieCredits(movieId)

    suspend fun getVideoItemFromNetwork(movieId: Int) = apiService.getMovieVideos(movieId)

}

interface RemoteDataSource {
    suspend fun getMoviesResultFromNetwork(categoryName: String, page: Int): Response<MovieResult>
    suspend fun getMovieDetailsFromNetwork(movieId: Int): Response<MovieDetailsItem>
    suspend fun getCreditItemFromNetwork(movieId: Int): Response<CreditsItem>
    suspend fun getVideoItemFromNetwork(movieId: Int): Response<VideoItem>
}