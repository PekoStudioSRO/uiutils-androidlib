package cz.pekostudio.uiutils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

fun Float.dp(): Float = convertTo(TypedValue.COMPLEX_UNIT_DIP)

fun Int.dp(): Int = convertTo(TypedValue.COMPLEX_UNIT_DIP).toInt()

fun Float.sp(): Float = convertTo(TypedValue.COMPLEX_UNIT_SP)

fun Int.sp(): Int = convertTo(TypedValue.COMPLEX_UNIT_SP).toInt()

private fun Number.convertTo(unit: Int): Float {
    return TypedValue.applyDimension(unit, this.toFloat(), Resources.getSystem().displayMetrics)
}