package com.itzikpich.moviesapp.view_holders

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.itzikpich.moviesapp.adapters.GenericAdapter
import com.itzikpich.moviesapp.loadFromUrlToGlide
import com.itzikpich.moviesapp.models.CreditsItem
import kotlinx.android.synthetic.main.item_cast.view.*

class CastItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GenericAdapter.Binder<CreditsItem.Cast> {

    private var imageView: AppCompatImageView = itemView.image_item_cast
    private var textView: AppCompatTextView = itemView.title_item_cast

    override fun bind(data: CreditsItem.Cast) {
        imageView.loadFromUrlToGlide(data.getFullImagePath())
        textView.text = data.name
    }
}
