package com.itzikpich.moviesapp.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieResult(
    @SerializedName("page")
    val page: Int?, // 1
    @SerializedName("results")
    val movies: List<Movie?>?,
    @SerializedName("total_pages")
    val totalPages: Int?, // 42
    @SerializedName("total_results")
    val totalResults: Int? // 836
) {
    data class Dates(
        @SerializedName("maximum")
        val maximum: String?, // 2021-03-26
        @SerializedName("minimum")
        val minimum: String? // 2021-02-06
    )

    @Entity
    data class Movie(
        @PrimaryKey
        val id: Int?, // 527774
        val adult: Boolean?, // false
        @SerializedName("backdrop_path")
        val backdropPath: String?, // /hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("original_language")
        val originalLanguage: String?, // en
        @SerializedName("original_title")
        val originalTitle: String?, // Raya and the Last Dragon
        val overview: String?,
        val popularity: Double?, // 4286.849
        @SerializedName("poster_path")
        val posterPath: String?, // /lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg
        @SerializedName("release_date")
        val releaseDate: String?, // 2021-03-03
        val title: String?, // Raya and the Last Dragon
        val video: Boolean?, // false
        @SerializedName("vote_average")
        val voteAverage: Double?, // 8.4
        @SerializedName("vote_count")
        val voteCount: Int? // 1459
    ) : Serializable {

        fun getFullImagePath() = "https://image.tmdb.org/t/p/w500/$posterPath"
    }
}