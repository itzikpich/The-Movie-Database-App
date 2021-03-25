package com.itzikpich.moviesapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.models.CategoryItem
import com.itzikpich.moviesapp.view_holders.CategoryViewHolder

abstract class CategoriesAdapter: GenericAdapter<CategoryItem>() {

    var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    fun updateItemsByCategory(catgory: CategoryItem) {
        (mRecyclerView?.findViewHolderForAdapterPosition(getIndexByCategory(catgory)) as? CategoryViewHolder)?.let { catViewHolder ->
            catgory.movies?.filterNotNull()?.let { catViewHolder.updateMovies(it) }
        } ?: run {
            updateItem(catgory)
        }

    }

    fun getIndexByCategory(catgory: CategoryItem) : Int {
        return listItems.indexOf(listItems.find { it.id == catgory.id })
    }

}