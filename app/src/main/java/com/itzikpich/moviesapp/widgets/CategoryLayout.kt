package com.itzikpich.moviesapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.itzikpich.moviesapp.R
import com.itzikpich.moviesapp.adapters.MoviesPagedAdapter
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.utilities.PaginationListener
import kotlinx.android.synthetic.main.layout_category.view.*

class CategoryLayout: RelativeLayout {

    var categoryItem: CategoryItem? = null
        private set

    var moviesAdapter: MoviesPagedAdapter? = null

    var categoryLayoutInputListener: CategoryLayoutInputListener? = null
        private set

    private var currentPage: Int = 1
    private val isLastPage: Boolean
        get() = currentPage == categoryItem?.totalPages
    private var isLoading = false

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, categoryItem: CategoryItem, categoryLayoutInputListener: CategoryLayoutInputListener) : super(context) {
        init(categoryItem, categoryLayoutInputListener)
    }

    constructor(context: Context, attrs: AttributeSet?, categoryItem: CategoryItem, categoryLayoutInputListener: CategoryLayoutInputListener) : super(context, attrs) {
        init(categoryItem, categoryLayoutInputListener)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, categoryItem: CategoryItem, categoryLayoutInputListener: CategoryLayoutInputListener) : super(context, attrs, defStyleAttr) {
        init(categoryItem, categoryLayoutInputListener)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_category, this, true)
    }

    private fun init(categoryItem: CategoryItem? = null, categoryLayoutInputListener: CategoryLayoutInputListener? = null) {

        this.categoryItem = categoryItem
        this.categoryLayoutInputListener = categoryLayoutInputListener

        this.more_button_layout_category.setOnClickListener {
            categoryItem?.id?.let { it1 -> this.categoryLayoutInputListener?.onMoreButtonClicked(it1, categoryItem.title) }
        }
        this.title_layout_category.text = categoryItem?.title
        this.title_layout_category_top.isVisible = false

        moviesAdapter = this.categoryItem?.let {
            MoviesPagedAdapter(it, object : MovieItemListener {

                override fun onItemClicked(id: Int, title: String) {
                    categoryLayoutInputListener?.onItemClicked(id, title)
                }

            })
        }

        this.recyclerview_layout_category.adapter = moviesAdapter
        this.recyclerview_layout_category.addOnScrollListener(object : PaginationListener(this.recyclerview_layout_category.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                doCall()
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
        moviesAdapter?.submitList(categoryItem?.movies)
//        categoryItem?.movies?.forEach { moviesList ->
//            moviesList?.let { it -> moviesAdapter.addItem(it) }
//        }

    }

    public fun updateItems(items: List<MovieResult.Movie>){
        moviesAdapter?.submitList(items)
        isLoading = false
    }

    private fun doCall() {
//        categoryItem?.id?.let { it1 -> fragment?.onRefresh(it1, currentPage) }
        categoryItem?.id?.let { categoryLayoutInputListener?.onRefreshList(it, currentPage) }
    }

    interface CategoryLayoutInputListener {
        fun onMoreButtonClicked(id: Int, title: String)
        fun onItemClicked(id: Int, title: String)
        fun onRefreshList(id: Int, page: Int)
    }

    interface MovieItemListener {
        fun onItemClicked(id: Int, title: String)
    }
}