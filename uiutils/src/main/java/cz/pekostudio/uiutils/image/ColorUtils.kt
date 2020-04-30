package cz.pekostudio.uiutils.image

import android.graphics.Color

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

fun Int.createWithAlpha(alpha: Int): Int {
    return Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
}