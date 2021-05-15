package com.itzikpich.moviesapp.data.local

import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.VideoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val moviesDatabase: MoviesDatabase
) : LocalDataSource {

    //region ROOM DB

    override fun loadCategory(id: Int): Flow<CategoryItem> = moviesDatabase.moviesDao().loadCategoryId(id)

    override fun addCategory(categoryItem: CategoryItem) = moviesDatabase.moviesDao().saveCategory(categoryItem)

    override fun loadMovieDetailsItem(id: Int): Flow<MovieDetailsItem> = moviesDatabase.moviesDao().loadMovieDetailsItemId(id)

    override fun addMovieDetailsItem(movieDetailsItem: MovieDetailsItem) = moviesDatabase.moviesDao().saveMovieDetailsItem(movieDetailsItem)

    override fun loadVideoItem(id: Int): Flow<VideoItem> = moviesDatabase.moviesDao().loadVideoItemId(id)

    override fun addVideoItem(videoItem: VideoItem) = moviesDatabase.moviesDao().saveVideoItem(videoItem)

    override fun loadCreditsItem(id: Int): Flow<CreditsItem> = moviesDatabase.moviesDao().loadCreditsItemId(id)

    override fun addCreditsItem(creditsItem: CreditsItem) = moviesDatabase.moviesDao().saveCreditsItem(creditsItem)

    //endregion

}

interface LocalDataSource {
    fun loadCategory(id: Int): Flow<CategoryItem>
    fun addCategory(categoryItem: CategoryItem)
    fun loadMovieDetailsItem(id: Int): Flow<MovieDetailsItem>
    fun addMovieDetailsItem(movieDetailsItem: MovieDetailsItem)
    fun loadVideoItem(id: Int): Flow<VideoItem>
    fun addVideoItem(videoItem: VideoItem)
    fun loadCreditsItem(id: Int): Flow<CreditsItem>
    fun addCreditsItem(creditsItem: CreditsItem)
}