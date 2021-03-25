package com.itzikpich.moviesapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class VideoItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?, // 550
    @SerializedName("results")
    val results: List<Video?>?
) {
    data class Video(
        @SerializedName("id")
        val id: String?, // 5c9294240e0a267cd516835f
        @SerializedName("iso_639_1")
        val iso6391: String?, // en
        @SerializedName("iso_3166_1")
        val iso31661: String?, // US
        @SerializedName("key")
        val key: String?, // BdJKm16Co6M
        @SerializedName("name")
        val name: String?, // Fight Club | #TBT Trailer | 20th Century FOX
        @SerializedName("site")
        val site: String?, // YouTube
        @SerializedName("size")
        val size: Int?, // 1080
        @SerializedName("type")
        val type: String? // Trailer
    ) {
//        fun getFullImagePath() = "https://image.tmdb.org/t/p/w500/$profilePath"

    }
}