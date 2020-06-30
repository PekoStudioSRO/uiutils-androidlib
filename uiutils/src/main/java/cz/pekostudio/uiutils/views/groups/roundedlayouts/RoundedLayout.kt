package cz.pekostudio.uiutils.views.groups.roundedlayouts

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import cz.pekostudio.uiutils.R
import java.lang.IllegalStateException

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

open class RoundedLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseRoundedLayout(context, attrs, defStyleAttr) {

    private var shapeBackgroundColor: Int = Color.WHITE

    init {
        outlineProvider = ViewOutlineProvider.BACKGROUND
        clipToOutline = true

        shapeBackgroundColor = getBackgroundColorFromAttributes()
        reloadDrawable()
    }

    override fun initAttributes(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.RoundedLayout).run {

            getCornerFromAttribute(R.styleable.RoundedLayout_round_radius)?.let {
                setCorners(it)
            }

            getCornerFromAttribute(R.styleable.RoundedLayout_round_radius_topleft)?.let {
                setCornerTo(it, 0, 1)
            }

            getCornerFromAttribute(R.styleable.RoundedLayout_round_radius_topright)?.let {
                setCornerTo(it, 2, 3)
            }

            getCornerFromAttribute(R.styleable.RoundedLayout_round_radius_bottomright)?.let {
                setCornerTo(it, 4, 5)
            }

            getCornerFromAttribute(R.styleable.RoundedLayout_round_radius_bottomleft)?.let {
                setCornerTo(it, 6, 7)
            }

            recycle()
        }
    }

    private fun getBackgroundColorFromAttributes(): Int {
        return when (background) {
            is ColorDrawable -> (background as ColorDrawable).color
            null -> Color.WHITE
            else -> throw IllegalStateException("Background drawable must be ColorDrawable or null")
        }
    }

    private fun reloadDrawable() {
        background = ShapeDrawable().apply {
            shape = RoundRectShape(radii, null, null).apply {
                paint.color = shapeBackgroundColor
            }
        }
    }
}