package com.namshi.customer.ui.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemGridBinding
import com.namshi.customer.model.Image
import com.namshi.customer.utils.ActionListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.load
import com.namshi.customer.utils.onClick

/**
 * Displays products in a grid
 * */
class ProductGridAdapter(private val listener: ActionListener) :
    RecyclerView.Adapter<ProductGridAdapter.Holder>() {

    private val items: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        ).apply {
            itemView.onClick() {
                val image = items[adapterPosition]
                listener.onItemClick(image)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val image = items[holder.adapterPosition]
        ItemGridBinding.bind(holder.itemView).apply {
            gridImage load image
        }
    }

    override fun getItemCount() = items.size

    fun setData(data: List<Image>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}