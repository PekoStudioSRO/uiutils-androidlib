package cz.pekostudio.uiutils.views.loading

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import androidx.core.graphics.alpha
import cz.pekostudio.uiutils.image.withAlpha
import cz.pekostudio.uiutils.views.setVisiblity
import cz.pekostudio.uiutils.views.startGroupTransition
import java.lang.IllegalStateException

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

open class LoadingFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val loadingView = LoadingView(context).also {
        super.addView(it)
    }

    public var loading = false
    set(value) {
        field = value
        loadingView.loading = value
    }
    get() = loadingView.loading

}