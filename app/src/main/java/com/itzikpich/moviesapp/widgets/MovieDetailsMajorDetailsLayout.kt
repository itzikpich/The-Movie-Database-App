package com.itzikpich.moviesapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.utilities.loadFromUrlToGlide
import com.itzikpich.moviesapp.models.MovieDetailsItem
import com.itzikpich.moviesapp.models.MovieResult
import kotlinx.android.synthetic.main.layout_movie_details_major_details.view.*

class MovieDetailsMajorDetailsLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_movie_details_major_details, this, true)
    }

    fun setData(movie: MovieDetailsItem) {
        movie_details_image.loadFromUrlToGlide(movie.getFullImagePath())
        movie_details_budget.text = context.getString(R.string.budget, movie.budget.toString())
        movie_details_release.text = context.getString(R.string.release, movie.releaseDate)
        movie_details_runtime.text = context.getString(R.string.runtime, movie.runtime)
        movie_details_rating.text = context.getString(R.string.rating, movie.voteAverage.toString())
        movie_details_status.text = context.getString(R.string.status, movie.status)
    }

}