package com.itzikpich.moviesapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: RetrofitService
) {

    suspend fun getMoviesResultFromNetwork(categoryName: String, page: Int) = apiService.getMovieResult(categoryName, page)

    suspend fun getMovieDetailsFromNetwork(movieId: Int) = apiService.getMovieDetails(movieId)

    suspend fun getCreditItemFromNetwork(movieId: Int) = apiService.getMovieCredits(movieId)

    suspend fun getVideoItemFromNetwork(movieId: Int) = apiService.getMovieVideos(movieId)

}