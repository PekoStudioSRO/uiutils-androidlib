package cz.pekostudio.uiutils

import android.content.Context
import android.widget.Toast
import androidx.annotation.IntRange
import androidx.annotation.StringRes

/**
 * Created by Lukas Urbanek on 25/06/2020.
 */

fun Context.toast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes id: Int) =
    Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show()

fun Context.toast(text: String, @IntRange(from = Toast.LENGTH_SHORT.toLong(), to = Toast.LENGTH_LONG.toLong()) length: Int) =
    Toast.makeText(this, text, length).show()

fun Context.toast(@StringRes id: Int,  @IntRange(from = Toast.LENGTH_SHORT.toLong(), to = Toast.LENGTH_LONG.toLong()) length: Int) =
    Toast.makeText(this, getString(id), length).show()