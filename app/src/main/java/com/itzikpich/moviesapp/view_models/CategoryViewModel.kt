package com.itzikpich.moviesapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itzikpich.moviesapp.data.MoviesRepository
import com.itzikpich.moviesapp.models.CategoryItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

     val TAG = "CategoryViewModel"

    val categoryMutableLiveData = MutableLiveData<CategoryItem>()

    fun getLocalCategory(id: Int) {
        viewModelScope.launch {
            moviesRepository.localCategory(id).collect { category ->
                categoryMutableLiveData.value = category
            }
        }
    }

}