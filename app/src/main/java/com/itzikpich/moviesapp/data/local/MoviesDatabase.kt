package com.itzikpich.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.VideoItem
import com.itzikpich.moviesapp.type_converters.CastListConverter
import com.itzikpich.moviesapp.type_converters.GenreListConverter
import com.itzikpich.moviesapp.type_converters.MovieListConverter
import com.itzikpich.moviesapp.type_converters.VideoListConverter

@Database(entities = [CategoryItem::class, MovieDetailsItem::class, VideoItem::class, CreditsItem::class], version = 1)
@TypeConverters(GenreListConverter::class, MovieListConverter::class, CastListConverter::class, VideoListConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}