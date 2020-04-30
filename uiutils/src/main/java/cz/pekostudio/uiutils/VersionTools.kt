package cz.pekostudio.uiutils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager


/**
 * Created by Lukas Urbanek on 27/04/2020.
 */


fun getAppVersionString(context: Context, debug: Boolean = false, versionString: String = "version"): String? {
    return try {
        val packageInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        "$versionString ${packageInfo.versionName} (build ${packageInfo.versionCode}${if (debug) ", debug" else ""})"
    } catch (e: PackageManager.NameNotFoundException) {
        e.toString()
    }
}

fun onlyApi(api: Int, block: () -> Unit) {
    if (android.os.Build.VERSION.SDK_INT >= api) {
        block()
    }
}

fun isApi(api: Int): Boolean {
    return android.os.Build.VERSION.SDK_INT >= api
}