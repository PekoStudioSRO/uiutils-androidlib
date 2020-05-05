package cz.pekostudio.uiutils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

val Float.dp: Float get() = convertTo(TypedValue.COMPLEX_UNIT_DIP)

val Int.dp: Int get() = convertTo(TypedValue.COMPLEX_UNIT_DIP).toInt()

val Float.sp: Float get() = convertTo(TypedValue.COMPLEX_UNIT_SP)

val Int.sp: Int get() = convertTo(TypedValue.COMPLEX_UNIT_SP).toInt()

private fun Number.convertTo(unit: Int): Float {
    return TypedValue.applyDimension(unit, this.toFloat(), Resources.getSystem().displayMetrics)
}