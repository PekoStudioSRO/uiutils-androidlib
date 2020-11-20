package cz.pekostudio.uiutils.views.input

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import cz.pekostudio.uiutils.runOnUiThread
import java.util.*

/**
 * Created by Lukas Urbanek on 15/05/2020.
 */

fun EditText.onImeActionClicked(
    onActionClicked: () -> Unit
) {
    val actions = arrayOf(
        EditorInfo.IME_ACTION_DONE,
        EditorInfo.IME_ACTION_NEXT,
        EditorInfo.IME_ACTION_SEARCH,
        EditorInfo.IME_ACTION_SEND,
        EditorInfo.IME_ACTION_GO,
        EditorInfo.IME_ACTION_PREVIOUS
    )
    setOnEditorActionListener { _, actionId, _ ->
        if (actions.contains(actionId)) {
            onActionClicked()
            true
        } else false
    }
}

@Deprecated("Use EditText.onImeActionClicked", ReplaceWith("onImeActionClicked(onDoneClicked)"))
fun EditText.onImeDoneClicked(onDoneClicked: () -> Unit) {
    onImeActionClicked(onDoneClicked)
}

val EditText.inputLayout: TextInputLayout
    get() = ((parent as ViewGroup).parent as TextInputLayout)

fun EditText.setOnTextChanged(onlySignificantChange: Boolean = false, listener: (String) -> Unit) {
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