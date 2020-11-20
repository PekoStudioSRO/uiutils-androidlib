package cz.pekostudio.uiutils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
/**
 * Created by Lukas Urbanek on 18/06/2020.
 */

val Float.half: Float get() = this.div(2)
val Float.quarter: Float get() = this.div(4)

val Int.half: Int get() = this.div(2)
val Int.quarter: Int get() = this.div(4)

val Double.half: Double get() = this.div(2)
val Double.quarter: Double get() = this.div(4)

/**
 * @author Miroslav Hýbler
 * @param digits -> number of digits after decimal point
 * @return string -> formatted number
 * @sample 123456.formatDecimal(2) -> "123 456,00"
 */
fun Number.formatDecimal(digits: Int = 0): String =
    DecimalFormat("#,###${ if (digits > 0) "." + "0".repeat(digits) else "" }").format(this).format(Locale.getDefault())

/**
 * @author Miroslav Hýbler
 * @param -> you can specify Locale or use default
 * @return String -> formatted number with currency on correct place
 * @sample 123456.formatCurrency() -> "123 456,00 Kč"
 */
fun Number.formatCurrency(locale: Locale = Locale.getDefault()): String =
    NumberFormat.getCurrencyInstance(locale).format(this)