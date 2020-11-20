package cz.pekostudio.uiutils.image

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

fun Context.imageLoader(): ImageLoader = ImageLoader(this)

fun View.imageLoader(): ImageLoader = context.imageLoader()

fun RecyclerView.ViewHolder.imageLoader(): ImageLoader = itemView.context.imageLoader()

fun Dialog.imageLoader(): ImageLoader = context.imageLoader()


fun ImageView.load(url: String, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(url, this, transition, placeholder)
}

fun ImageView.load(uri: Uri, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(uri, this, transition, placeholder)
}

fun ImageView.load(file: File, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(file, this, transition, placeholder)
}

fun ImageView.load(sourceId: Int, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(sourceId, this, transition, placeholder)
}

fun ImageView.load(drawable: Drawable, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(drawable, this, transition, placeholder)
}


