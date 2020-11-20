package cz.pekostudio.uiutils

import android.content.Intent
import android.os.Bundle
import kotlin.random.Random

/**
 * Created by Lukas Urbanek on 16/09/2020.
 */


fun Intent.dumpIntent(): String {
    val bundle: Bundle = extras ?: return "null"
    val it = bundle.keySet().iterator()
    return buildString {
        while (it.hasNext()) {
            val key = it.next()
            append("[$key=${bundle.get(key)}]")
        }
    }
}

fun randomImageUrl(xResolution: Int = 800, yResolution: Int = 800): String {
    return "https://i.picsum.photos/id/${Random.nextInt(800)}/$xResolution/$yResolution.jpg"
}