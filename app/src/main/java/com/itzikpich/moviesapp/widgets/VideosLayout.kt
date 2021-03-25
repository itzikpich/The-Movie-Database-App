package com.itzikpich.moviesapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.VideoPagedAdapter
import com.itzikpich.moviesapp.models.VideoItem
import kotlinx.android.synthetic.main.layout_category.view.*

class VideosLayout: RelativeLayout {

    lateinit var videoItem: VideoItem
        private set

    constructor(context: Context) : super(context) {
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context, videoItem: VideoItem) : super(context) {
        this.videoItem = videoItem
        init()
    }

    fun setVideosItem(videoItem: VideoItem) {
        this.videoItem = videoItem
        init()
    }


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_category, this, true)
    }

    private fun init() {

        this.title_layout_category.text = context.getString(R.string.videos)
        this.more_button_layout_category.isVisible = false
        this.title_layout_category_top.isVisible = false

        val adapter = VideoPagedAdapter()

        this.recyclerview_layout_category.adapter = adapter
        adapter.submitList(videoItem.results)
    }

}