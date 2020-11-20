package cz.pekostudio.uiutils.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Interpolator
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.NestedScrollView
import cz.pekostudio.uiutils.dp
import cz.pekostudio.uiutils.runDelayed

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

@Deprecated("Use isVisible from ktx-core")
fun View.setVisiblity(visible: Boolean?, invisibleAsGone: Boolean = true) {
    visibility = if (visible == true) {
        View.VISIBLE
    } else {
        if (invisibleAsGone) {
            View.GONE
        } else {
            View.INVISIBLE
        }
    }
}

fun View.hideIfNull(data: Unit?, invisibleAsGone: Boolean = true) {
    visibility = if (data != null) {
        View.VISIBLE
    } else {
        if (invisibleAsGone) {
            View.GONE
        } else {
            View.INVISIBLE
        }
    }
}

fun ViewGroup.startGroupTransition(
    duration: Long = 120,
    interpolator: Interpolator? = null,
    ordering: Int = TransitionSet.ORDERING_TOGETHER
) {
    TransitionManager.beginDelayedTransition(this,
        getAutoTransition(
            duration,
            interpolator,
            ordering
        )
    )
}

fun View.startParentTransition(
    duration: Long = 120,
    interpolator: Interpolator? = null,
    ordering: Int = TransitionSet.ORDERING_TOGETHER
) {
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup,
        getAutoTransition(
            duration,
            interpolator,
            ordering
        )
    )
}

private fun getAutoTransition(
    duration: Long,
    interpolator: Interpolator?,
    ordering: Int
): AutoTransition {
    return AutoTransition().also { transition ->
        interpolator?.let {
            transition.interpolator = it
        }
        transition.ordering = ordering
        transition.duration = duration
    }
}

fun ViewGroup.findViewsByTag(tag: String, onViewFind: (view: View) -> Unit) {
    childs {
        if (it.tag == tag) {
            onViewFind(it)
        }
        if (it is ViewGroup) {
            it.findViewsByTag(tag, onViewFind)
        }
    }
}

fun View.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.run {
        setMargins(left, top, right, bottom)
    }
}

@JvmName("filteredChilds")
inline fun <reified O : View> ViewGroup.childs(result: (O) -> Unit) {
    repeat(childCount) {
        getChildAt(it)?.let { view ->
            if (view::class == O::class) {
                result(view as O)
            }
        }
    }
}

inline fun ViewGroup.childs(result: (View) -> Unit) {
    repeat(childCount) {
        getChildAt(it)?.let { view ->
            result(view)
        }
    }
}

@JvmName("filteredChildsIndexed")
inline fun <reified O : View> ViewGroup.childsIndexed(result: (O, index: Int) -> Unit) {
    var index = 0
    repeat(childCount) {
        getChildAt(it)?.let { view ->
            if (view::class == O::class) {
                result(view as O, index)
                index++
            }
        }
    }
}

inline fun ViewGroup.childsIndexed(result: (View, index: Int) -> Unit) {
    var index = 0
    repeat(childCount) {
        getChildAt(it)?.let { view ->
            result(view, index)
            index++
        }
    }
}

fun View.onGlobalLayout(block: () -> Unit) {
    val view = this
    view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            block()
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }

    })
}

fun View.scrollHere() {

    fun View.disableScroll(disabled: Boolean) { tag = disabled }

    fun View.isScrollDisabled() = tag as? Boolean == true

    var viewYPosInScrollView = top

    fun View.findScrollView(): FrameLayout? = when (parent) {
        is NestedScrollView -> parent
        is ScrollView -> parent
        else -> (parent as? ViewGroup)?.apply {
            viewYPosInScrollView += top
        }?.findScrollView()
    } as? FrameLayout?

    findScrollView()?.run {
        if (isScrollDisabled()) return
        disableScroll(true)
        when(this) {
            is NestedScrollView -> smoothScrollTo(0, viewYPosInScrollView - 18.dp, 500)
            is ScrollView -> smoothScrollTo(0, viewYPosInScrollView - 18.dp)
        }
        runDelayed(500) { disableScroll(false) }
    }
}

inline fun <reified T: View> ViewGroup.findFirstViewByType(): T? {
    childs<T> { return it }
    return null
}