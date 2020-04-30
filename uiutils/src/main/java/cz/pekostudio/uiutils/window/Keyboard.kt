package cz.pekostudio.uiutils.window

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager

/**
 * Created by Lukas Urbanek on 9/18/2018.
 */
object Keyboard {

    fun toggle(context: Context) {
        context.getInputSerivce().toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun show(activity: Activity) {
        activity.getInputSerivce().showSoftInput(activity.window.decorView.rootView, 0)
    }

    fun hide(activity: Activity) {
        activity.getInputSerivce().hideSoftInputFromWindow(activity.window.decorView.rootView.windowToken, 0)
    }

    fun hide(context: Context, windowToken: IBinder) {
        context.getInputSerivce().hideSoftInputFromWindow(windowToken, 0)
    }

    private fun Context.getInputSerivce(): InputMethodManager {
        return (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    }
}
