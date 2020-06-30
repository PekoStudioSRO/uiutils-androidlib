package cz.pekostudio.uiutils.views.groups.roundedlayouts

import android.content.Context
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import cz.pekostudio.uiutils.R

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

open class ClippingRoundedLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseRoundedLayout(context, attrs, defStyleAttr) {

    internal lateinit var path: Path

    private var viewOutlineProvider: ViewOutlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setConvexPath(path)
        }
    }

    override fun initAttributes(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.ClippingRoundedLayout).run {

            getCornerFromAttribute(R.styleable.ClippingRoundedLayout_clip_round_radius)?.let {
                setCorners(it)
            }

            getCornerFromAttribute(R.styleable.ClippingRoundedLayout_clip_round_radius_topleft)?.let {
                setCornerTo(it, 0, 1)
            }

            getCornerFromAttribute(R.styleable.ClippingRoundedLayout_clip_round_radius_topright)?.let {
                setCornerTo(it, 2, 3)
            }

            getCornerFromAttribute(R.styleable.ClippingRoundedLayout_clip_round_radius_bottomright)?.let {
                setCornerTo(it, 4, 5)
            }

            getCornerFromAttribute(R.styleable.ClippingRoundedLayout_clip_round_radius_bottomleft)?.let {
                setCornerTo(it, 6, 7)
            }

            recycle()
        }

        onChanged()
    }

    override fun draw(canvas: Canvas) {
        canvas.run {
            save()
            drawFilter = PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.ANTI_ALIAS_FLAG)
            clipPath(path)
            outlineProvider = viewOutlineProvider
            clipToOutline = true
            super.draw(this)
            restore()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        onChanged()
    }

    override fun onChanged() {
        super.onChanged()
        path = Path().apply {
            addRoundRect(RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat()), radii, Path.Direction.CW)
            close()
        }
        invalidate()
    }
}