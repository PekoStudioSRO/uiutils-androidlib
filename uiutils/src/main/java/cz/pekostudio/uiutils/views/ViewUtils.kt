package cz.pekostudio.uiutils.views

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Interpolator

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