package cz.pekostudio

import android.app.Activity
import android.graphics.Color
import android.widget.TextView
import cz.pekostudio.uiutils.R
import cz.pekostudio.uiutils.formatDecimal
import cz.pekostudio.uiutils.image.withAlpha
import cz.pekostudio.uiutils.views.simplelist.SimpleRecyclerView

/**
 * Created by Lukas Urbanek on 04/05/2020.
 */
fun Activity.main() {

    val list = arrayListOf("item 1", "item 2", "item 3")

    (Color.BLACK withAlpha 0).run {

    }

    findViewById<SimpleRecyclerView>(0).apply {
        setSimpleAdapter(
            data = list,
            layout = 0,
            holder = {
                object {
                    val textView = findViewById<TextView>(R.id.text)
                }
            }
        ) { item, _ ->
            textView.text = item
        }
        setOnDataRequest {

        }
    }
}