package com.namshi.customer.ui.main.adapters

import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
abstract class BaseAdapter<T : RecyclerView.ViewHolder?>(private var fragment: Fragment? = null) :
    RecyclerView.Adapter<T>(), LifecycleObserver {

    protected var rv: RecyclerView? = null



    init {
        fragment?.viewLifecycleOwner?.lifecycle?.addObserver(this)
    }

    open fun onStart() {
        notifyDataSetChanged()

    }

    open fun onStop() {
        rv?.children
            ?.mapIndexed { _, view -> rv?.getChildViewHolder(view) }
            ?.filterNotNull()
            ?.forEach { clearHolder(it as T) }
    }

    open fun onPause() {
    }

    open fun onResume() {
    }

    open fun onDestroy() {
        fragment = null
        rv = null
    }

    /**
     * Children can override this method to clear of resources for all view holders available
     * in recycler view at the moment
     * Helpful to release glide resources
     */
    open fun clearHolder(holder: T) {
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        rv = null
    }

    /**
     * Checks if a given position is valid with conditions
     * - is not RecyclerView.NO_POSITION
     * - does not exceed getItemCount
     *
     * This must be overridden and used if itemCount is not equal to attached list size
     * */
    open fun isValidPos(position: Int): Boolean {
        return position != RecyclerView.NO_POSITION && position < itemCount
    }

    internal fun viewHolderForAdapterPosition(position: Int) =
        rv?.findViewHolderForAdapterPosition(position) as? T

    internal fun viewHolderForLayoutPosition(position: Int) =
        rv?.findViewHolderForLayoutPosition(position) as? T


}

abstract class BaseViewHolder<T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    abstract fun bind(data: T)
}

interface LayoutContainer {
    val containerView: View
}
