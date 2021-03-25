package com.itzikpich.moviesapp.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieResult

class CastListConverter {

    @TypeConverter
    fun fromCastList(list: List<CreditsItem.Cast?>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<CreditsItem.Cast?>?>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toCastList(string: String?): List<CreditsItem.Cast>? {
        if (string == null) {
            return null
        }
        val type = object : TypeToken<List<CreditsItem.Cast?>?>() {}.type
        return Gson().fromJson<List<CreditsItem.Cast>>(string, type)
    }

}