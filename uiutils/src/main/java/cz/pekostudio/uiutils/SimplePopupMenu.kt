package cz.pekostudio.uiutils

import android.view.Menu
import android.view.View
import android.widget.PopupMenu

import androidx.annotation.CheckResult
import cz.pekostudio.uiutils.views.simplelist.SimpleRecyclerView

import java.util.ArrayList

/**
 * Created by Lukas Urbanek on 8.1.19.
 */
class SimplePopupMenu(
    private val view: View
) {
    public val popupMenu: PopupMenu = PopupMenu(view.context, view)
    private var menu: Menu = popupMenu.menu
    private var anyClickListener: (() -> Unit)? = null
    private val items = ArrayList<PopupMenuItem>()

    private inner class PopupMenuItem internal constructor(
        internal val title: String,
        internal val clickListener: () -> Unit
    )

    fun addItem(title: String, listener: () -> Unit) {
        items.add(PopupMenuItem(title, listener))
    }

    fun addOnAnyClicked(anyClickListener: () -> Unit) {
        this.anyClickListener = anyClickListener
    }

    fun addDissmisListner(dismissListener: PopupMenu.OnDismissListener) {
        popupMenu.setOnDismissListener(dismissListener)
    }

    private fun build() {
        menu.clear()
        for (i in items.indices) menu.add(0, i, i, items[i].title)
        popupMenu.setOnMenuItemClickListener { item ->
            items[item.itemId].clickListener()
            anyClickListener?.let { it() }
            false
        }
    }

    fun show() {
        build()
        popupMenu.show()
    }
}
