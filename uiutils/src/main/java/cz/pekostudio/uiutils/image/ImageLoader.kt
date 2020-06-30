package cz.pekostudio.uiutils.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */
class ImageLoader(context: Context) {

    private val glide = Glide.with(context)

    public fun loadAsBitmap(url: String, onBitmapReady: (bitmap: Bitmap) -> Unit) {
        glide
            .asBitmap()
            .load(url)
            .into(object: SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    onBitmapReady.invoke(resource)
                }
            })
    }

    public fun loadIntoView(
        url: String,
        imageView: ImageView,
        transition: Boolean = true,
        placeholder: Drawable? = null
    ) {
        glide
            .load(url)
            .transition(getTransition(transition))
            .placeholder(placeholder)
            .into(imageView)
    }
}

private fun getTransition(enabled: Boolean): DrawableTransitionOptions {
    return withCrossFade(
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(enabled).build()
    )
}
