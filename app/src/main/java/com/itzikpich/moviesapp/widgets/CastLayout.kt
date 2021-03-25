package com.itzikpich.moviesapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.CastPagedAdapter
import com.itzikpich.moviesapp.adapters.MoviesPagedAdapter
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.CreditsItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.utilities.PaginationListener
import com.itzikpich.moviesapp.views.HomeFragment
import kotlinx.android.synthetic.main.layout_category.view.*

class CastLayout: RelativeLayout {

    lateinit var creditsItem: CreditsItem
        private set

    constructor(context: Context) : super(context) {
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context, creditsItem: CreditsItem) : super(context) {
        this.creditsItem = creditsItem
        init()
    }

    fun setCreditsItem(creditsItem: CreditsItem) {
        this.creditsItem = creditsItem
        init()
    }


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_category, this, true)
    }

    private fun init() {

        this.more_button_layout_category.isVisible = false

        this.title_layout_category.text = context.getString(R.string.casts)
        this.title_layout_category_top.isVisible = true

        val moviesAdapter = CastPagedAdapter()

        this.recyclerview_layout_category.adapter = moviesAdapter
        moviesAdapter.submitList(creditsItem.cast)
    }

}