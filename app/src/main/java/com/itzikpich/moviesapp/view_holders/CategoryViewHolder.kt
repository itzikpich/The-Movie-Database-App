package com.itzikpich.moviesapp.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.adapters.GenericAdapter
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.models.MovieResult
import com.itzikpich.moviesapp.widgets.CategoryLayout
import kotlinx.android.synthetic.main.item_empty_layout.view.*

class CategoryViewHolder(itemView: View, private val categoryLayoutInputListener: CategoryLayout.CategoryLayoutInputListener) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<CategoryItem> {
//class CategoryViewHolder(itemView: View, val fragment: HomeFragment) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<CategoryItem> {

    val parent = itemView.parent_empty_layout

    var categoryLayout: CategoryLayout? = null

    override fun bind(data: CategoryItem) {
        parent.removeAllViews()
        categoryLayout = CategoryLayout(itemView.context, data, object: CategoryLayout.CategoryLayoutInputListener{
            override fun onMoreButtonClicked(id: Int, title: String) {
                categoryLayoutInputListener.onMoreButtonClicked(id, title)
            }

            override fun onItemClicked(id: Int, title: String) {
                categoryLayoutInputListener.onItemClicked(id, title)
            }

            override fun onRefreshList(id: Int, page: Int) {
                categoryLayoutInputListener.onRefreshList(id, page)
            }
        })
//        categoryLayout = CategoryLayout(itemView.context, data, object : CategoryLayout.CategoryLayoutInputListener {
//            override fun onMoreButtonClicked(id: Int) {
//
//            }
//
//            override fun onItemClicked(id: Int) {
//                id
//            }
//
//            override fun onRefreshList(id: Int, page: Int) {
//
//            }
//
//        })

        parent.addView(categoryLayout)
    }

    fun updateMovies(movies: List<MovieResult.Movie>) {
        categoryLayout?.updateItems(movies)
    }
}
