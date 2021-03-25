package com.itzikpich.moviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.view_holders.CastItemViewHolder
import com.itzikpich.moviesapp.view_holders.GridItemViewHolder
import com.itzikpich.moviesapp.view_holders.LargeImageViewHolder
import com.itzikpich.moviesapp.view_holders.SmallImageViewHolder
import com.itzikpich.moviesapp.widgets.CategoryLayout

class CastPagedAdapter : ListAdapter<CreditsItem.Cast, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as? CastItemViewHolder)?.apply {
                bind(item)
            }
        }
        else Log.e("item is null", "")
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CreditsItem.Cast>() {
            override fun areItemsTheSame(oldItem: CreditsItem.Cast, newItem: CreditsItem.Cast): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CreditsItem.Cast, newItem: CreditsItem.Cast): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}