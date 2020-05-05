package cz.pekostudio.uiutils.views.simplelist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cz.pekostudio.datetimepickers.R
import java.util.*

/**
 * Created by Lukas Urbanek on 30.4.20.
 */
open class SimpleRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var dataRefreshManager: DataRefreshManager? = null

    init {
        attrs?.let {
            initAttributes(it)
        }
    }

    private fun initAttributes(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.SimpleRecyclerView).run {

            getBoolean(R.styleable.SimpleRecyclerView_list_recycling_enabled, true).let {
                if (!it) recycledViewPool.setMaxRecycledViews(0, 0)
            }

            getInt(R.styleable.SimpleRecyclerView_list_orientation, 0).let {
                layoutManager = when (it) {
                    1 -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    2 -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    3 -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                    else -> LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
                }
            }

            recycle()
        }
    }

    public fun <V, O> setAdapter(
        data: ArrayList<O>,
        layout: Int,
        holder: View.() -> V,
        onViewBind: V.(item: O, position: Int) -> Unit
    ) {
        adapter = SimpleAdapter(context, data, layout, holder, onViewBind)
    }

    public fun setOnDataRequest(onRefreshRequested: () -> Unit) {
        dataRefreshManager = DataRefreshManager(onRefreshRequested)
    }

    private class SimpleAdapter<O, V> internal constructor(
        private val context: Context,
        private val data: ArrayList<O>,
        private val layout: Int,
        private val holder: View.() -> V,
        private val onViewBind: V.(item: O, position: Int) -> Unit
    ) : RecyclerView.Adapter<SimpleAdapter.ViewHolder<V>>() {

        private class ViewHolder<V> internal constructor(itemView: View, val views: V) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(root: ViewGroup, viewType: Int): ViewHolder<V> {
            return (LayoutInflater.from(context).inflate(layout, root, false) as View).let {
                ViewHolder(it, holder(it))
            }
        }

        override fun onBindViewHolder(holder: ViewHolder<V>, position: Int) {
            onViewBind(holder.views, data[position], position)
        }

        override fun getItemCount(): Int = data.size
    }

    private inner class DataRefreshManager(val onRefreshRequested: () -> Unit) {

        private var swipeRefreshLayout: SwipeRefreshLayout? = null
        internal var onDataReloadRequest: (() -> Unit)? = null

        init {
            swipeRefreshLayout = findSwipeRefreshLayout()?.apply {
                setOnRefreshListener { onRefreshRequested() }
            }

        }

        private fun findSwipeRefreshLayout(): SwipeRefreshLayout? {
            (parent as? ViewGroup)?.let {
                while (true) {
                    return if (it.parent is SwipeRefreshLayout) it as SwipeRefreshLayout else null
                }
            }
            return null
        }

    }

    public fun requestDataReload() {
        dataRefreshManager?.onDataReloadRequest?.invoke()
    }

    public fun notifyDataSetChanged() {
        adapter?.notifyDataSetChanged()
    }

}
