package com.itzikpich.moviesapp.utilities

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.IOException
import java.io.InputStream

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
