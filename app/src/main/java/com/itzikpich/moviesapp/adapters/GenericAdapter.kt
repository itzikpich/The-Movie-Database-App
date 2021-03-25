package com.itzikpich.moviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected var listItems: MutableList<T>
    private var clickAction: ((T)->Unit)?

    constructor(listItems: MutableList<T>, clickAction:((T)->Unit)? = null) {
        this.listItems = listItems
        this.clickAction = clickAction
    }

    constructor(clickAction:((T)->Unit)? = null) {
        listItems = ArrayList()
        this.clickAction = clickAction
    }

    fun setList(listItems: List<T>) {
        if (listItems.isNotEmpty()) {
            this.listItems.addAll(listItems)
            notifyDataSetChanged()
        }
    }

    fun addItem(listItem: T) {
        this.listItems.add(listItem)
        notifyItemInserted(listItems.size-1)
    }

    fun updateItem(listItem: T) {
        if(this.listItems.contains(listItem)) {
            this.listItems.remove(listItem)
        }
        addItem(listItem)
    }

    fun updateFirstItem(listItem: T) {
        if(this.listItems.contains(listItem)) {
            this.listItems.remove(listItem)
        }
        this.listItems.add(0, listItem)
        notifyItemInserted(0)
    }

    fun addItemByPos(listItem: T, position: Int) {
        this.listItems.add(position, listItem)
        notifyItemInserted(position)
    }

    fun replaceList(@Nullable listItems: List<T>) {
        this.listItems.clear()
        this.listItems.addAll(listItems)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.listItems.clear()
        notifyDataSetChanged()

    }


    fun T.removeItem() {
        if (listItems.isNotEmpty()) {
            this@GenericAdapter.listItems.remove(this)
            notifyDataSetChanged()
        }
    }

    //    inflate a view based on viewType passed to it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = getViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)

    //   we can type cast holder as Binder and bind our data with the views.
    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (listItems[position] != null) (holder as Binder<T>).bind(listItems[position])
        holder.itemView.setOnClickListener {
            clickAction?.invoke((listItems[position]))
//            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int = getLayoutId(position, listItems[position])

    //    abstract function which returns the layout to inflate, implemented while creating the adapter
    protected abstract fun getLayoutId(position: Int, obj: T): Int

    //    abstract function which returns Recyclerview.ViewHolder, implemented while creating the adapter
    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    //    viewHolder will implement this so that we can bind data with views
    internal interface Binder<T> {
        fun bind(data: T)
    }
}