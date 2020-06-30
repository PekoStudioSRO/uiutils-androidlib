package cz.pekostudio.uiutils.views.loading

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cz.pekostudio.uiutils.R
import cz.pekostudio.uiutils.dp
import cz.pekostudio.uiutils.image.withAlpha
import cz.pekostudio.uiutils.views.setVisiblity
import cz.pekostudio.uiutils.views.startGroupTransition

/**
 * Created by Lukas Urbanek on 30/04/2020.
 */

open class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading, this)
        findViewById<View>(R.id.bg).apply {
            setBackgroundColor(Color.BLACK withAlpha 100)
        }
        setVisiblity(false)
        translationZ = 200f.dp
    }

    public var loading = false
    set(value) {
        field = value
        startGroupTransition()
        setVisiblity(field)
    }

}