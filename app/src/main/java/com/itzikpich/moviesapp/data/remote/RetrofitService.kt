package com.itzikpich.moviesapp.data.remote

import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.models.VideoItem
import com.itzikpich.moviesapp.utilities.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("3/movie/{categoryPath}")
    suspend fun getMovieResult(
        @Path(value = "categoryPath", encoded = true) category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ) : Response<MovieResult>

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ) : Response<MovieDetailsItem>

    @GET("3/movie/{movieId}/videos")
    suspend fun getMovieVideos(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ) : Response<VideoItem>

    @GET("3/movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ) : Response<CreditsItem>

}