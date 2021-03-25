package com.itzikpich.moviesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class CategoryItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val path: String,
    var totalPages: Int?,
    var movies: List<MovieResult.Movie?>?
) {
    companion object {
        fun parseJsonToList(gson: Gson, json: String): List<CategoryItem>? = gson.fromJson<List<CategoryItem>>(json, object: TypeToken<List<CategoryItem>>() {}.type)
    }
}