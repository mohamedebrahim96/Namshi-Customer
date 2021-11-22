package com.namshi.customer.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemImageSubBinding
import com.namshi.customer.model.NamshiResponse
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class ImageWidgetAdapter :
    BindingListAdapter<NamshiResponse.Content.Images, ImageWidgetAdapter.ViewHolder>(diffUtil) {

    //private val items: MutableList<NamshiResponse.Content.Images> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        parent.binding<ItemImageSubBinding>(R.layout.item_image_sub).let(::ViewHolder)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindContent(getItem(position))


    inner class ViewHolder constructor(
        private val binding: ItemImageSubBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindContent(image: NamshiResponse.Content.Images) {
            binding.image = image
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<NamshiResponse.Content.Images>() {

            override fun areItemsTheSame(
                oldItem: NamshiResponse.Content.Images,
                newItem: NamshiResponse.Content.Images
            ): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(
                oldItem: NamshiResponse.Content.Images,
                newItem: NamshiResponse.Content.Images
            ): Boolean =
                oldItem == newItem
        }
    }
}
