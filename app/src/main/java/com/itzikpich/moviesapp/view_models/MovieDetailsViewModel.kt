package com.itzikpich.moviesapp.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itzikpich.moviesapp.data.MoviesRepository
import com.itzikpich.moviesapp.data.Result
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.VideoItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    // region MovieDetailsItem

    var movieDetailsLiveData : MutableLiveData<MovieDetailsItem?> = MutableLiveData()

    fun getMovieFromLocal(id: Int) {
        viewModelScope.launch {
            moviesRepository.loadMovieDetailsItem(id).collect { details ->
                movieDetailsLiveData.value = details
            }
        }
    }

    fun getMovieDetailsFromRemote(movieId: Int) = with(moviesRepository) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getMovieDetailsFromRemote(movieId).collect { result ->
                    when (result) {
                        is Result.Error -> {
                        }
                        is Result.Loading -> {
                        }
                        is Result.Success -> {
                            result.data?.let {
                                moviesRepository.addMovieDetailsItem(it)
                            }
                        }
                    }
                }
            }
        }
    }

    // endregion

    // region CreditsItem

    var creditsItemLiveData : MutableLiveData<CreditsItem?> = MutableLiveData()

    fun getCreditsFromLocal(id: Int) {
        viewModelScope.launch {
            moviesRepository.loadCreditsItem(id).collect { details ->
                creditsItemLiveData.postValue(details)
            }
        }
    }

    fun getCreditsFromRemote(movieId: Int) = with(moviesRepository) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCreditsItemFromRemote(movieId).collect { result ->
                    when (result) {
                        is Result.Error -> {
                        }
                        is Result.Loading -> {
                        }
                        is Result.Success -> {
                            result.data?.let {
                                moviesRepository.addCreditsItem(it)
                            }
                        }
                    }
                }
            }
        }
    }

    // endregion

    // region VideosItem

    var videosItemLiveData : MutableLiveData<VideoItem?> = MutableLiveData()

    fun getVideosFromLocal(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesRepository.loadVideoItem(id).collect { details ->
                    videosItemLiveData.postValue(details)
                }
            }
        }
    }

    fun getVideoItemFromRemote(movieId: Int) = with(moviesRepository) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getVideoItemFromRemote(movieId).collect { result ->
                    when (result) {
                        is Result.Error -> {
                        }
                        is Result.Loading -> {
                        }
                        is Result.Success -> {
                            result.data?.let {
                                moviesRepository.addVideoItem(it)
                            }
                        }
                    }
                }
            }
        }
    }

    // endregion

}