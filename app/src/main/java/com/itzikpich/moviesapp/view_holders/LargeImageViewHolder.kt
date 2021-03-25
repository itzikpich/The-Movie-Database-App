package com.itzikpich.moviesapp.view_holders

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.GenericAdapter
import com.itzikpich.moviesapp.utilities.loadFromUrlToGlide
import com.itzikpich.moviesapp.models.MovieResult
import kotlinx.android.synthetic.main.item_large.view.*

class LargeImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<MovieResult.Movie> {

    private var imageView: AppCompatImageView = itemView.image_item_large
    private var textView: AppCompatTextView = itemView.title_item_large

    override fun bind(data: MovieResult.Movie) {
        textView.text = data.title
        imageView.loadFromUrlToGlide(data.getFullImagePath())
    }
}
