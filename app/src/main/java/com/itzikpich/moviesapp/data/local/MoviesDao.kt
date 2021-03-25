package com.itzikpich.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itzikpich.moviesapp.models.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategories(movies: List<CategoryItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCategory(movies: CategoryItem)

    @Query("SELECT * FROM categoryitem WHERE id = :id")
    fun loadCategoryId(id: Int): Flow<CategoryItem>

    @Query("SELECT * FROM categoryitem")
    fun loadCategories(): Flow<List<CategoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovieDetailsItem(movies: MovieDetailsItem)

    @Query("SELECT * FROM movieDetailsItem WHERE id = :id")
    fun loadMovieDetailsItemId(id: Int): Flow<MovieDetailsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVideoItem(video: VideoItem)

    @Query("SELECT * FROM videoitem WHERE id = :id")
    fun loadVideoItemId(id: Int): Flow<VideoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCreditsItem(creditsItem: CreditsItem)

    @Query("SELECT * FROM creditsitem WHERE id = :id")
    fun loadCreditsItemId(id: Int): Flow<CreditsItem>

}