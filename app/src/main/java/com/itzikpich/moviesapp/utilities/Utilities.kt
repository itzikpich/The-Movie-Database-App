package com.itzikpich.moviesapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itzikpich.moviesapp.utilities.GlideApp
import java.io.IOException
import java.io.InputStream


inline fun <reified T> Gson.parseJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

//inline fun <reified T> Gson.parseJsonToListOf(json: String) = this.fromJson<List<T>>(json, object: TypeToken<List<NumberItem>>() {}.type)

/**
 * Converts dp to pixel
 */
val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Converts pixel to dp
 */
val Int.pxToDp: Int get() = (this / Resources.getSystem().displayMetrics.density).toInt()

//fun String.parseToItemable(gson: Gson) : Itemable = gson.fromJson(this, Itemable::class.java)

fun loadAsset(context: Context, filename: String) : String? {
    return try {
        val `is`: InputStream = context.assets.open(filename)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, charset("UTF-8"))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
}

/**
 * Extension method to load imageView from url.
 */
fun ImageView.loadFromUrlToGlide(imageUrl: String?, onResourceReady: () -> Unit = {}) {
    GlideApp.with(this).asBitmap().load(imageUrl).listener(object : RequestListener<Bitmap> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>?,
            isFirstResource: Boolean
        ): Boolean {
            onResourceReady.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Bitmap?,
            model: Any?,
            target: Target<Bitmap>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onResourceReady.invoke()
            return false
        }
    }).into(this)
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}
