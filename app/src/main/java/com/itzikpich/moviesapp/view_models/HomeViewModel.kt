package com.itzikpich.moviesapp.view_models

import androidx.lifecycle.*
import com.itzikpich.moviesapp.data.MoviesRepository
import com.itzikpich.moviesapp.data.Result
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var categoryLiveData : MutableLiveData<CategoryItem> = MutableLiveData()
    var categoryByIdLiveData : MutableLiveData<CategoryItem> = MutableLiveData()

    fun getLocalCategory(id: Int) {
        viewModelScope.launch {
            moviesRepository.localCategory(id).collect { category ->
                categoryByIdLiveData.value = category
            }
        }
    }

    fun getCategoryFromLocal(id: Int) {
        viewModelScope.launch {
            moviesRepository.localCategory(id).collect { category ->
                categoryLiveData.value = category
            }
        }
    }

    fun getMultipleMovies(categoryItem: List<CategoryItem>, page: Int = 1) {
        viewModelScope.launch {
            supervisorScope {
                categoryItem.forEach { category ->
                    withContext(Dispatchers.Default) {
                        moviesRepository.getMovieResultFromRemote(category.path, page).collect { result ->
                            when(result) {
                                is Result.Error -> {
                                }
                                is Result.Loading -> {
                                }
                                is Result.Success -> {
                                    category.totalPages = result.data?.totalPages
                                    category.movies = result.data?.movies
                                    moviesRepository.addCategoryToLocal(category)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun getMovies(category: CategoryItem, page: Int) = with(moviesRepository){
        viewModelScope.launch {
            getMovieResultFromRemote(category.path, page).collect { result ->
                when(result) {
                    is Result.Error -> {
                    }
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        category.totalPages = result.data?.totalPages
                        result.data?.movies?.let { newList ->
                            category.movies = category.movies?.toMutableList()?.apply {
                                addAll(newList)
                            }
                        }
                        moviesRepository.addCategoryToLocal(category)
                    }
                }
            }
        }
    }

}