package com.namshi.customer.ui.details.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemGridBinding
import com.namshi.customer.model.Image
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.load

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class DetailsAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<DetailsAdapter.Holder>() {

    private val items: MutableList<Image> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid, parent, false)
        ).apply {
            itemView.setOnClickListener {
                val image = items[bindingAdapterPosition]
                listener.onItemClick(image)
            }
        }
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val image = items[holder.bindingAdapterPosition]
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