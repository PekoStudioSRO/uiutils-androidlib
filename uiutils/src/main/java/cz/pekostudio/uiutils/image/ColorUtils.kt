package cz.pekostudio.uiutils.image

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

infix fun Int.withAlpha(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}

fun ImageView.setTintColor(color: Int) {
    imageTintList = ColorStateList.valueOf(color)
}