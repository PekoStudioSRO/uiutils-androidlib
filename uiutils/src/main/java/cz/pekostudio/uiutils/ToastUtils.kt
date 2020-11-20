package cz.pekostudio.uiutils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Created by Lukas Urbanek on 25/06/2020.
 */

fun Context.toast(text: String?, length: Int = Toast.LENGTH_SHORT) {
    runOnUiThread {
        Toast.makeText(this, text, length).show()
    }
}

fun Context.toast(@StringRes stringRes: Int, length: Int = Toast.LENGTH_SHORT) {
    runOnUiThread {
        Toast.makeText(this, stringRes, length).show()
    }
}

fun String?.showToast(context: Context?, length: Int = Toast.LENGTH_SHORT) {
    runOnUiThread {
        Toast.makeText(context, this, length).show()
    }
}