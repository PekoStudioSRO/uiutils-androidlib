package cz.pekostudio.uiutils

/**
 * Created by Lukas Urbanek on 18/06/2020.
 */

val Float.half: Float get() = (this).div(2)
val Float.quarter: Float get() = (this).div(4)

val Int.half: Int get() = (this).div(2)
val Int.quarter: Int get() = (this).div(4)

val Double.half: Double get() = (this).div(2)
val Double.quarter: Double get() = (this).div(4)

/**
 * @param digits - počet požadovaných desetinných míst
 * @return string, ex. 3,14159.format(2) -> 3.14
 */
fun Number.format(digits: Int): String {
    return "%.${digits}f".format(this).replace(",", ".")
}