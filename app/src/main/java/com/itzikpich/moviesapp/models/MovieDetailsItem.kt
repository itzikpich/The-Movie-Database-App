package com.itzikpich.moviesapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDetailsItem(
    @PrimaryKey
    val id: Int?, // 550
    val adult: Boolean?, // false
    @SerializedName("backdrop_path")
    val backdropPath: String?, // /52AfXWuXCHn3UjD17rBruA9f5qb.jpg
    val budget: Int?, // 63000000
    val homepage: String?, // http://www.foxmovies.com/movies/fight-club
    @SerializedName("imdb_id")
    val imdbId: String?, // tt0137523
    @SerializedName("original_language")
    val originalLanguage: String?, // en
    @SerializedName("original_title")
    val originalTitle: String?, // Fight Club
    val overview: String?, // A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
    val popularity: Double?, // 53.449
    @SerializedName("poster_path")
    val posterPath: String?, // /8kNruSfhk5IoE4eZOc4UpvDn6tq.jpg
    @SerializedName("release_date")
    val releaseDate: String?, // 1999-10-15
    val revenue: Int?, // 100853753
    val runtime: Int?, // 139
    val status: String?, // Released
    val tagline: String?, // Mischief. Mayhem. Soap.
    val title: String?, // Fight Club
    val video: Boolean?, // false
    @SerializedName("vote_average")
    val voteAverage: Double?, // 8.4
    @SerializedName("vote_count")
    val voteCount: Int? // 21328
) {
    fun getFullImagePath() = "https://image.tmdb.org/t/p/w500/$posterPath"
}