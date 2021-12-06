package com.namshi.customer.ui.details.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemGridBinding
import com.namshi.customer.model.Image
import com.namshi.customer.utils.ClickListener
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class DetailsAdapter(private val listener: ClickListener) :
    BindingListAdapter<Image, DetailsAdapter.ViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        parent.binding<ItemGridBinding>(R.layout.item_grid).let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindImage(getItem(position))

    inner class ViewHolder constructor(
        private val binding: ItemGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.onItemClick(getItem(bindingAdapterPosition))
            }
        }

        fun bindImage(imagee: Image) {
            binding.image = imagee
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Image>() {

            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.height == newItem.height

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem == newItem
        }
    }
}