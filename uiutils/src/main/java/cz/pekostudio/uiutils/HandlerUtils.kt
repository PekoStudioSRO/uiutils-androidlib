package cz.pekostudio.uiutils

import android.os.Handler
import android.os.Looper

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