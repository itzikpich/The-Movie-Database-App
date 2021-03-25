package com.itzikpich.moviesapp.view_holders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.adapters.GenericAdapter
import com.itzikpich.moviesapp.loadFromUrlToGlide
import com.itzikpich.moviesapp.models.VideoItem
import kotlinx.android.synthetic.main.item_video.view.*

class VideoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<VideoItem.Video> {

    private var imageView: AppCompatImageView = itemView.image_item_video

    override fun bind(data: VideoItem.Video) {
//        imageView.loadFromUrlToGlide(data.getFullImagePath())
    }
}
