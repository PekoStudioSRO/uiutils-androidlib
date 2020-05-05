package cz.pekostudio.uiutils

import android.os.Handler
import android.os.Looper
import cz.pekostudio.datetimepickers.R

/**
 * Created by Lukas Urbanek on 24/04/2020.
 */

fun runDelayed(duration: Long, onUiThread: Boolean = true, block: () -> Unit) {
    (if (onUiThread) Handler(Looper.getMainLooper()) else Handler()).postDelayed({
        block()
    }, duration)
}

fun runOnUiThread(block: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        block()
    }
}

inline fun <T, R> T.runIf(condition: Boolean, block: T.() -> R): R? {
    return if (condition) block() else null
}

inline fun <T, R> T.letIf(condition: Boolean, block: (T) -> R): R? {
    return if (condition) block(this) else null
}

inline fun <T> T.applyIf(condition: Boolean, block: T.() -> Unit): T {
    if (condition) block()
    return this
}

inline fun <T> T.alsoIf(condition: Boolean, block: (T) -> Unit): T {
    if (condition) block(this)
    return this
}