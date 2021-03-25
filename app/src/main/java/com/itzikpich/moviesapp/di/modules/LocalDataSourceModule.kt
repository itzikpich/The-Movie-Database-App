package com.itzikpich.moviesapp.di.modules

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.itzikpich.moviesapp.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideGson() : Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, MoviesDatabase::class.java, "movies-db").build()
}