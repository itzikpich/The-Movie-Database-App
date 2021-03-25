package com.itzikpich.moviesapp.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.models.VideoItem

class VideoListConverter {

    @TypeConverter
    fun fromVideoList(list: List<VideoItem.Video?>?): String? {
        if (list == null) {
            return null
        }
        val type = object : TypeToken<List<VideoItem.Video?>?>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toVideoList(string: String?): List<VideoItem.Video>? {
        if (string == null) {
            return null
        }
        val type = object : TypeToken<List<VideoItem.Video?>?>() {}.type
        return Gson().fromJson<List<VideoItem.Video>>(string, type)
    }

}