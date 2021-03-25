package com.itzikpich.moviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.view_holders.GridItemViewHolder
import com.itzikpich.moviesapp.widgets.CategoryLayout

class GridMoviesPagedAdapter(private val categoryItem: CategoryItem, private val movieItemListener: CategoryLayout.MovieItemListener) : ListAdapter<MovieResult.Movie, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_small, parent, false)
        return GridItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as? GridItemViewHolder)?.apply {
                bind(item)
                itemView.setOnClickListener {
                    item.id?.let { it1 ->
                        movieItemListener.onItemClicked(it1, item.title ?: "")
                    }
                }
            }
        }
        else Log.e("item is null", "")
    }

    override fun getItemViewType(position: Int): Int = when(categoryItem.id) {
        0 -> R.layout.item_large
        else -> R.layout.item_small
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResult.Movie>() {
            override fun areItemsTheSame(oldItem: MovieResult.Movie, newItem: MovieResult.Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieResult.Movie, newItem: MovieResult.Movie): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}