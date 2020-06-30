package cz.pekostudio.uiutils

/**
 * Created by Lukas Urbanek on 26/06/2020.
 */

fun <T> ArrayList<T>.update(data: ArrayList<T>) {
    clear()
    addAll(data)
}