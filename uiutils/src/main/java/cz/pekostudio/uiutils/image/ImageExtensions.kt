package cz.pekostudio.uiutils.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

fun Context.imageLoader(): ImageLoader {
    return ImageLoader(this)
}

fun ImageView.loadImage(url: String, transition: Boolean = true, placeholder: Drawable? = null) {
    ImageLoader(context).loadIntoView(url, this, transition, placeholder)
}