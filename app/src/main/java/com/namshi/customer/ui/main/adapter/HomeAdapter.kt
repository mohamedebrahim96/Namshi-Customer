package com.namshi.customer.ui.main.adapter


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemHomeBinding
import com.namshi.customer.model.NamshiResponse
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class HomeAdapter : BindingListAdapter<NamshiResponse.Content, HomeAdapter.ViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        parent.binding<ItemHomeBinding>(R.layout.item_home).let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindContent(getItem(position))

    inner class ViewHolder constructor(
        private val binding: ItemHomeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun bindContent(content: NamshiResponse.Content) {
            binding.content = content
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<NamshiResponse.Content>() {

            override fun areItemsTheSame(
                oldItem: NamshiResponse.Content,
                newItem: NamshiResponse.Content
            ): Boolean =
                oldItem.type == newItem.type

            override fun areContentsTheSame(
                oldItem: NamshiResponse.Content,
                newItem: NamshiResponse.Content
            ): Boolean =
                oldItem == newItem
        }
    }
}
