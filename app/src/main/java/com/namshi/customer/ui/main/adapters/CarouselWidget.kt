package com.namshi.customer.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.namshi.customer.R
import com.namshi.customer.base.BaseAdapter
import com.namshi.customer.base.BaseViewHolder
import com.namshi.customer.databinding.ItemCarouselSubBinding
import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.utils.ActionListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.load

/**
 * Adapter to display [NamshiWidget.Type]
 * */
class CarouselWidget(private val listener: ActionListener) :
    BaseAdapter<CarouselWidget.Holder>() {
    private val items: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_sub, parent, false)
        ).apply {
            itemView.setOnClickListener {
                val image = items[adapterPosition]
                listener.onItemClick(image)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[holder.adapterPosition]
        holder.bind(widget)
    }

    override fun getItemCount() = items.size

    fun setData(data: List<Image>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : BaseViewHolder<Image>(itemView) {
        override fun bind(data: Image) {
            val binding = ItemCarouselSubBinding.bind(itemView)
            binding.carouselImage load data
        }
    }
}