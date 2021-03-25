package com.itzikpich.moviesapp.view_holders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.adapters.GenericAdapter
import com.itzikpich.moviesapp.utilities.loadFromUrlToGlide
import com.itzikpich.moviesapp.models.MovieResult
import kotlinx.android.synthetic.main.item_small.view.*

class GridItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<MovieResult.Movie> {

    private var imageView: AppCompatImageView = itemView.image_item_small
    private var textView: AppCompatTextView = itemView.title_item_small

    override fun bind(data: MovieResult.Movie) {
        imageView.loadFromUrlToGlide(data.getFullImagePath())
        textView.isInvisible = true
    }
}
