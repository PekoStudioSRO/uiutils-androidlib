package cz.pekostudio.uiutils.views.groups.roundedlayouts

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

abstract class BaseRoundedLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    protected var radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

    init {
        attrs?.let {
            initAttributes(it)
        }
    }

    abstract fun initAttributes(attrs: AttributeSet)

    public fun setCorners(topLeft: Float = 0f, topRight: Float = 0f, bottomRight: Float = 0f, bottomLeft: Float = 0f) {
        setCornerTo(topLeft, 0, 1)
        setCornerTo(topRight, 2, 3)
        setCornerTo(bottomRight, 4, 5)
        setCornerTo(bottomLeft, 6, 7)
        onChanged()
    }

    public fun setCorners(corners: Float) {
        for (i in radii.indices) radii[i] = corners
        onChanged()
    }

    protected fun setCornerTo(corner: Float, vararg index: Int) {
        index.forEach {
            radii[it] = corner
        }
    }

    protected fun TypedArray.getCornerFromAttribute(index: Int): Float? {
        getDimension(index, -1f).let {
            return if (it != -1f) it else null
        }
    }

    protected open fun onChanged() {

    }

}