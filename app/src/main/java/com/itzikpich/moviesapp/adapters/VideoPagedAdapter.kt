package com.itzikpich.moviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.models.VideoItem
import com.itzikpich.moviesapp.view_holders.VideoItemViewHolder

class VideoPagedAdapter : ListAdapter<VideoItem.Video, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as? VideoItemViewHolder)?.apply {
                bind(item)
            }
        }
        else Log.e("item is null", "")
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideoItem.Video>() {
            override fun areItemsTheSame(oldItem: VideoItem.Video, newItem: VideoItem.Video): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VideoItem.Video, newItem: VideoItem.Video): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}