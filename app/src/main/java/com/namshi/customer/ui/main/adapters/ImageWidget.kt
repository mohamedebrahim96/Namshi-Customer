package com.namshi.customer.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.namshi.customer.R
import com.namshi.customer.base.BaseAdapter
import com.namshi.customer.base.BaseViewHolder
import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.databinding.ItemImageSubBinding
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.load

/**
 * Takes a list of images and displays them in a column of full width of screen.
 * */
class ImageWidget(private val listener: ClickListener) : BaseAdapter<ImageWidget.Holder>() {

    private val items: MutableList<Image> = mutableListOf()
    private var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_sub, parent, false)).apply {
            itemView.setOnClickListener() {
                val item = items[adapterPosition]
                listener.onItemClick(item)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[position]
        holder.bind(widget)
    }

    override fun getItemCount() = count

    fun setData(data: NamshiWidget) {
        items.clearAndAddAll(data.images)
        count = if (data.cols > 0) data.cols else 0
        notifyDataSetChanged()
    }


    class Holder(itemView: View) : BaseViewHolder<Image>(itemView) {
        override fun bind(data: Image) {
            ItemImageSubBinding
                .bind(itemView)
                .apply {
                    staticImage load data
                }
        }

    }
}