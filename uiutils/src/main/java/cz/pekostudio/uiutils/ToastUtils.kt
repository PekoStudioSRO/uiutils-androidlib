package cz.pekostudio.uiutils

import android.content.Context
import android.widget.Toast

/**
 * Created by Lukas Urbanek on 25/06/2020.
 */

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}