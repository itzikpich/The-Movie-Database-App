package com.itzikpich.moviesapp.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.itzikpich.moviesapp.models.MovieResult

class MovieListConverter {

    @TypeConverter
    fun fromCountryLangList(list: List<MovieResult.Movie?>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<MovieResult.Movie?>?>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toCountryLangList(string: String?): List<MovieResult.Movie>? {
        if (string == null) {
            return null
        }
        val type = object : TypeToken<List<MovieResult.Movie?>?>() {}.type
        return Gson().fromJson<List<MovieResult.Movie>>(string, type)
    }

}