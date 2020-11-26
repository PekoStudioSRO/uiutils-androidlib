package cz.pekostudio.uiutils.views.groups.roundedlayouts

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import cz.pekostudio.uiutils.R
import cz.pekostudio.uiutils.image.ImageLoader
import cz.pekostudio.uiutils.image.load
import java.io.File

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

open class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RoundedLayout(context, attrs, defStyleAttr) {

    public var imageView: ImageView? = null
    private var image: Drawable? = null

    override fun initAttributes(attrs: AttributeSet) {
        setCorners(100000f)

        context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView).run {

            getCornerFromAttribute(R.styleable.RoundedImageView_image_round_radius)?.let {
                setCorners(it)
            }

            image = getDrawable(R.styleable.RoundedImageView_image_round_src)

            recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        imageView = ImageView(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            scaleType = ImageView.ScaleType.CENTER_CROP
            image?.let {
                setImageDrawable(it)
            }
            addView(this)
        }
    }

    @Deprecated("this will be removed", ReplaceWith("load(drawable)") )
    public fun setImageDrawable(drawable: Drawable) {
        imageView?.setImageDrawable(drawable)
    }
    @Deprecated("this will be removed", ReplaceWith("load(bitmap)") )
    public fun setImageBitmap(bitmap: Bitmap) {
        imageView?.setImageBitmap(bitmap)
    }
    @Deprecated("this will be removed", ReplaceWith("load(url)") )
    public fun setImageUrl(url: String, transition: Boolean = true) {
        imageView?.let {
            ImageLoader(context)
                .loadIntoView(url, it, transition)
        }
    }

    public fun setImageResource(resource: Int) {
        imageView?.setImageResource(resource)
    }

    fun load(drawable: Drawable) {
       imageView?.load(drawable)
    }

    fun load(bitmap: Bitmap) {
        imageView?.load(bitmap)
    }

    fun load(file: File) {
        imageView?.load(file)
    }

    fun load(uri: Uri) {
        imageView?.load(uri)
    }

    fun load(url: String, transition: Boolean = true) {
        imageView?.load(url, transition)
    }

    fun load(resource: Int) {
        imageView?.setImageResource(resource)
    }
}