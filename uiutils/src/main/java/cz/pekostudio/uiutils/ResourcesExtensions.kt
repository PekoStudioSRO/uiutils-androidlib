package cz.pekostudio.uiutils

import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Lukas Urbanek on 08/06/2020.
 */

fun Context.color(@ColorRes resId: Int): Int = ContextCompat.getColor(this, resId)

fun Context.font(@FontRes resId: Int): Typeface = ResourcesCompat.getFont(this, resId)!!

fun Context.drawable(@DrawableRes resId: Int): Drawable = getDrawable(resId)!!

fun Context.tint(@ColorRes srcId: Int): ColorStateList = ContextCompat.getColorStateList(this, srcId)!!

fun Context.quantityString(@PluralsRes id: Int, int: Int, vararg params: Any): String = resources.getQuantityString(id, int, *params)


/**
 * referencing resources inside View from its context
 */

fun View.color(@ColorRes id: Int) = context.color(id)

fun View.drawable(@DrawableRes id: Int) = context.drawable(id)

fun View.font(@FontRes id: Int) = context.font(id)

fun View.tint(@ColorRes id: Int) = context.tint(id)

fun View.getString(@StringRes id: Int) = context.getString(id)

fun View.quantityString(@PluralsRes id: Int, int: Int, vararg params: Any) = context.quantityString(id, int, *params)


/**
 * referencing resources inside ViewHolder by context of its View
 */

fun RecyclerView.ViewHolder.color(@ColorRes id: Int) = itemView.context.color(id)

fun RecyclerView.ViewHolder.drawable(@DrawableRes id: Int) = itemView.context.drawable(id)

fun RecyclerView.ViewHolder.font(@FontRes id: Int) = itemView.context.font(id)

fun RecyclerView.ViewHolder.tint(@ColorRes id: Int) = itemView.context.tint(id)

fun RecyclerView.ViewHolder.getString(@StringRes id: Int) = itemView.context.getString(id)

fun RecyclerView.ViewHolder.quantityString(@PluralsRes id: Int, int: Int, vararg params: Any) = itemView.context.quantityString(id, int, *params)


/**
 * referencing resources inside Dialog from its context
 */

fun Dialog.color(@ColorRes id: Int) = context.color(id)

fun Dialog.drawable(@DrawableRes id: Int) = context.drawable(id)

fun Dialog.font(@FontRes id: Int) = context.font(id)

fun Dialog.tint(@ColorRes id: Int) = context.tint(id)

fun Dialog.getString(@StringRes id: Int) = context.getString(id)

fun Dialog.quantityString(@PluralsRes id: Int, int: Int, vararg params: Any) = context.quantityString(id, int, *params)

