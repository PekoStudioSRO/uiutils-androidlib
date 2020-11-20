package cz.pekostudio.uiutils

import android.util.Patterns
import java.util.regex.Pattern

/**
 * Created by Lukas Urbanek on 2019-10-14.
 */
class Validator(validator: Validator.() -> Unit) {

    private val validations: ArrayList<Data> = ArrayList()

    init {
        validator()
    }

    fun validation(input: ValidatableInput, errorText: String, success: () -> Boolean) {
        validations.add(
            Data(
                input,
                success,
                errorText
            )
        )
    }

    fun validation(success: () -> Boolean) {
        validations.add(
            Data(
                null,
                success,
                null
            )
        )
    }

    fun requiredValidation(input: ValidatableInput, errorText: String = "Chyba") {
        validation(input, errorText) {
            !input.text.isNullOrEmpty()
        }
    }

    fun patternValidation(input: ValidatableInput, pattern: Pattern, errorText: String = "Chyba") {
        validation(input, errorText) {
            pattern.matcher(input.text.toString()).matches() || input.text.isNullOrEmpty()
        }
    }

    fun emailValidation(input: ValidatableInput, errorText: String = "Neplatný e-mail") {
        patternValidation(input, Patterns.EMAIL_ADDRESS, errorText)
    }

    fun phoneValidation(input: ValidatableInput, errorText: String = "Neplatné telefonní číslo") {
        patternValidation(input, Patterns.PHONE, errorText)
    }

    fun urlValidation(input: ValidatableInput, errorText: String = "Neplatná URL adresa") {
        patternValidation(input, Patterns.WEB_URL, errorText)
    }

    fun check(): Boolean {
        reset()
        var success = true
        validations.forEach {
            if (it.success()) {
                it.input?.error = null
            } else {
                it.input?.error = it.errorText
                success = false
            }
        }
        return success
    }

    fun ifOkThen(block: () -> Unit) {
        if (check()) block()
    }

    fun reset() {
        for (validation in validations) validation.input?.error = null
    }

    class Data(
        val input: ValidatableInput?,
        val success: () -> Boolean,
        val errorText: String?
    )

    public interface ValidatableInput {
        var text: String?
        var error: String?
    }
}
