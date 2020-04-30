package cz.pekostudio.uiutils.window

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import cz.pekostudio.uiutils.onlyApi

/**
 * Created by Lukas Urbanek on 27.5.19.
 */

fun Activity.setLightStatusBar(light: Boolean = true, alwaysDarkOnDarkmode: Boolean = true) {
    onlyApi(23) {
        if (if (alwaysDarkOnDarkmode && isDarkMode(window.context)) false else light) {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
}

fun Activity.setLightNavgiationBar(light: Boolean = true, alwaysDarkOnDarkmode: Boolean = true) {
    onlyApi(26) {
        if (if (alwaysDarkOnDarkmode && isDarkMode(window.context)) false else light) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
    }
}

fun isDarkMode(context: Context): Boolean {
    return when ((context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> true
        else -> false
    }
}
