package com.namshi.customer.ui.main.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemSliderSubBinding
import com.namshi.customer.model.Image
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.load

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class SliderWidget(private val fragment: Fragment, private val listener: ClickListener) :
    BaseAdapter<SliderWidget.Holder>(fragment) {

    private val items: MutableList<Image> = mutableListOf()

    val handler = Handler(Looper.getMainLooper())

    private val autoScroll = object : Runnable {
        override fun run() {
            val lm = rv?.layoutManager
            if (lm !is LinearLayoutManager) return
            val position = lm.findFirstCompletelyVisibleItemPosition()

            if (position == RecyclerView.NO_POSITION) return

            if (position == itemCount.minus(1)) rv?.smoothScrollToPosition(0)
            else rv?.smoothScrollToPosition(position.plus(1))
            handler.postDelayed(this, AUT0_SCROLL_TIMER)
        }
    }

    private val autoScrollEnabler = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> handler.postDelayed(autoScroll, AUT0_SCROLL_TIMER)
                else -> handler.removeCallbacks(autoScroll)
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(autoScrollEnabler)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        recyclerView.removeOnScrollListener(autoScrollEnabler)
    }

    override fun onResume() {
        super.onResume()
        rv?.addOnScrollListener(autoScrollEnabler)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
        rv?.removeOnScrollListener(autoScrollEnabler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slider_sub, parent, false)
        ).apply {
            itemView.setOnClickListener {
                val item = items[bindingAdapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[holder.bindingAdapterPosition]
        holder.bind(widget)
    }

    override fun getItemCount() = items.size

    fun setData(data: List<Image>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
        handler.postDelayed(autoScroll, 1000)
    }

    class Holder(itemView: View) : BaseViewHolder<Image>(itemView) {
        override fun bind(data: Image) {
            val binding = ItemSliderSubBinding.bind(itemView)
            binding.sliderImage load data
        }
    }

    companion object {
        private const val AUT0_SCROLL_TIMER = 3000L
    }
}