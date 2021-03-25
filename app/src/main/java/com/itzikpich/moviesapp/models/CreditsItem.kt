package com.itzikpich.moviesapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CreditsItem(
    @PrimaryKey
    @SerializedName("id")
    val id: Int?, // 500
    @SerializedName("cast")
    val cast: List<Cast?>?
) {
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean?, // false
        @SerializedName("gender")
        val gender: Int?, // 2
        @SerializedName("id")
        val id: Int?, // 1037
        @SerializedName("known_for_department")
        val knownForDepartment: String?, // Acting
        @SerializedName("name")
        val name: String?, // Harvey Keitel
        @SerializedName("original_name")
        val originalName: String?, // Harvey Keitel
        @SerializedName("popularity")
        val popularity: Double?, // 6.51
        @SerializedName("profile_path")
        val profilePath: String?, // /nmhafZ605lZjs1CLcr9nSletOSd.jpg
        @SerializedName("cast_id")
        val castId: Int?, // 20
        @SerializedName("character")
        val character: String?, // Mr. White / Larry Dimmick
        @SerializedName("credit_id")
        val creditId: String?, // 52fe424ac3a36847f8012d01
        @SerializedName("order")
        val order: Int? // 0
    ) {
        fun getFullImagePath() = "https://image.tmdb.org/t/p/w500/$profilePath"
    }
}