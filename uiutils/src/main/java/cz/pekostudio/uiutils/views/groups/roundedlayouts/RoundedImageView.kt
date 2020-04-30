package cz.pekostudio.uiutils.views.groups.roundedlayouts

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import cz.pekostudio.datetimepickers.R
import cz.pekostudio.uiutils.image.ImageLoader

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

    public fun setImageDrawable(drawable: Drawable) {
        imageView?.setImageDrawable(drawable)
    }

    public fun setImageBitmap(bitmap: Bitmap) {
        imageView?.setImageBitmap(bitmap)
    }

    public fun setImageUrl(url: String, transition: Boolean = true) {
        imageView?.let {
            ImageLoader(context)
                .loadIntoView(url, it, transition)
        }
    }

    public fun setImageResource(resource: Int) {
        imageView?.setImageResource(resource)
    }
}