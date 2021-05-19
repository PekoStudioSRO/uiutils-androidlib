package cz.pekostudio.uiutils.window

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import cz.pekostudio.uiutils.onlyApi

/**
 * Created by Lukas Urbanek on 27.5.19.
 */

@Deprecated(
    message = "use Activity.setLightStatusBar(light: Boolean = true)",
    replaceWith = ReplaceWith("use Activity.setLightStatusBar(light: Boolean = true)")
)
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

fun Activity.setLightStatusBar(light: Boolean = true) {
    when {
        android.os.Build.VERSION.SDK_INT >= 30 -> {
            window?.insetsController?.run {
                if (light) {
                    setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
                } else {
                    setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
                }
            }
        }
        android.os.Build.VERSION.SDK_INT >= 23 -> {
            if (light) {
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
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
