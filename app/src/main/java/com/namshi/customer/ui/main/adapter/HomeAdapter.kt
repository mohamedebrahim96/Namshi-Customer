package com.namshi.customer.ui.main.adapter


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.databinding.ItemHomeBinding
import com.namshi.customer.databinding.ItemImageBinding
import com.namshi.customer.model.NamshiResponse
import com.skydoves.bindables.BindingListAdapter

class HomeAdapter : BindingListAdapter<NamshiResponse.Content, HomeAdapter.ViewHolder>(diffUtil) {

    private val imageViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            NamshiResponse.Content.Type.image.asInt ->
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                )
            else -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
            )

            //parent.binding<ItemHomeBinding>(R.layout.item_home).let(::ViewHolder)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(holder.adapterPosition)
        when (holder.itemViewType) {
            NamshiResponse.Content.Type.image.asInt -> {
                ItemImageBinding.bind(holder.itemView).apply {
                    content = item
                    imageRecycler.apply {
                        setRecycledViewPool(imageViewPool)
                        if (item.cols > 0) {
                            layoutManager = GridLayoutManager(context, item.cols)
                            adapter = ImageWidgetAdapter()
                        }
                    }
                }
            }
            else ->
                ItemHomeBinding.bind(holder.itemView).apply { content = getItem(position)
            }
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).type.asInt

    inner class ViewHolder constructor(private val binding: View) :
        RecyclerView.ViewHolder(binding)


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
