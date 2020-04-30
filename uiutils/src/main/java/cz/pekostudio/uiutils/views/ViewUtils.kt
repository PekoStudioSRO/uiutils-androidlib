package cz.pekostudio.uiutils.views

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

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
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child.tag == tag) {
            onViewFind(child)
        }
        if (child is ViewGroup) {
            child.findViewsByTag(tag, onViewFind)
        }
    }
}

fun View.setMargins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.run {
        setMargins(left, top, right, bottom)
    }
}