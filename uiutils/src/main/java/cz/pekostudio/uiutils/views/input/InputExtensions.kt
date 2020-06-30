package cz.pekostudio.uiutils.views.input

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import cz.pekostudio.uiutils.runOnUiThread
import java.text.Normalizer
import java.util.*

/**
 * Created by Lukas Urbanek on 15/05/2020.
 */

fun EditText.onDoneClicked(onDoneClicked: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
            onDoneClicked()
            true
        } else false
    }
}

val EditText.inputLayout: TextInputLayout
    get() = ((parent as ViewGroup).parent as TextInputLayout)

fun EditText.setOnTextChanged(onlySignificantChange: Boolean = true, listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        var lastCall: Timer? = null

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

        override fun afterTextChanged(s: Editable) = Unit

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (onlySignificantChange) {
                lastCall?.cancel()
                lastCall = Timer().apply {
                    schedule(
                        object : TimerTask() {
                            override fun run() {
                                runOnUiThread {
                                    listener(s.toString())
                                }
                            }
                        }, 400
                    )
                }
            } else {
                listener(s.toString())
            }
        }
    })
}