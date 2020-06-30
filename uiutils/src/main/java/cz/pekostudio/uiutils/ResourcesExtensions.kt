package cz.pekostudio.uiutils

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Created by Lukas Urbanek on 08/06/2020.
 */

fun Context.color(@ColorRes resId: Int): Int = ContextCompat.getColor(this, resId)

fun Context.font(@FontRes resId: Int): Typeface = ResourcesCompat.getFont(this, resId)!!

fun Context.drawable(@DrawableRes resId: Int): Drawable = getDrawable(resId)!!

fun Context.tint(@ColorRes srcId: Int): ColorStateList? {
    return ContextCompat.getColorStateList(this, srcId)
}