package com.itzikpich.moviesapp.data.local

import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.VideoItem
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val moviesDatabase: MoviesDatabase
) {

    //region ROOM DB

    fun loadAllCategories() = moviesDatabase.moviesDao().loadCategories()

    fun loadCategory(id: Int) = moviesDatabase.moviesDao().loadCategoryId(id)

    fun addCategory(categoryItem: CategoryItem) = moviesDatabase.moviesDao().saveCategory(categoryItem)

    fun loadMovieDetailsItem(id: Int) = moviesDatabase.moviesDao().loadMovieDetailsItemId(id)

    fun addMovieDetailsItem(movieDetailsItem: MovieDetailsItem) = moviesDatabase.moviesDao().saveMovieDetailsItem(movieDetailsItem)

    fun loadVideoItem(id: Int) = moviesDatabase.moviesDao().loadVideoItemId(id)

    fun addVideoItem(videoItem: VideoItem) = moviesDatabase.moviesDao().saveVideoItem(videoItem)

    fun loadCreditsItem(id: Int) = moviesDatabase.moviesDao().loadCreditsItemId(id)

    fun addCreditsItem(creditsItem: CreditsItem) = moviesDatabase.moviesDao().saveCreditsItem(creditsItem)

    //endregion

}