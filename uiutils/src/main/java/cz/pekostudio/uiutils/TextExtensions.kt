package cz.pekostudio.uiutils

import android.text.Html
import android.text.Spanned
import androidx.annotation.Keep
import androidx.core.text.HtmlCompat
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.Normalizer
import java.text.NumberFormat
import java.util.*

/**
 * Created by Lukas Urbanek on 20/05/2020.
 */

fun CharSequence.unaccent(): CharSequence {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return "\\p{InCombiningDiacriticalMarks}+".toRegex()
        .replace(temp, "")

}
infix fun String?.searchContains(to: String?): Boolean {
    val string1 = this?.toLowerCase(Locale.getDefault())?.unaccent()
    val string2 = to?.toLowerCase(Locale.getDefault())?.unaccent()
    return string2?.let { string1?.contains(it) } ?: false
}

fun String.remove(toRemove: String, ignoreCase: Boolean = false): String =
    replace(toRemove, "", ignoreCase = ignoreCase)


fun String.md5(): String {
    return try {
        StringBuilder().apply{
            MessageDigest.getInstance("MD5").apply {
                update(this@md5.toByteArray())
            }.digest().forEach {
                append(Integer.toHexString(0xFF and it.toInt()))
            }
        }.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        ""
    }
}

fun html(html: String): Spanned =
    HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)