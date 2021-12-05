package com.namshi.customer.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.R
import com.namshi.customer.base.BaseAdapter
import com.namshi.customer.databinding.ItemCarouselBinding
import com.namshi.customer.databinding.ItemImageBinding
import com.namshi.customer.databinding.ItemSliderBinding
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.clearAndAddAll
import com.namshi.customer.utils.gone

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class MainWidget(private val fragment: Fragment, private val listener: ClickListener) :
    BaseAdapter<MainWidget.Holder>(fragment) {

    private val items: MutableList<NamshiWidget> = mutableListOf()

    private val imageViewPool = RecyclerView.RecycledViewPool()
    private val carouselViewPool = RecyclerView.RecycledViewPool()
    private val sliderViewPool = RecyclerView.RecycledViewPool()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val holder = when (viewType) {
            NamshiWidget.Type.image.asInt -> Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            )
            NamshiWidget.Type.slider.asInt -> Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
            )
            NamshiWidget.Type.carousel.asInt -> Holder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
            )
            else -> Holder(View(parent.context))
        }
        return holder
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val widget = items[holder.bindingAdapterPosition]
        when (holder.itemViewType) {
            NamshiWidget.Type.image.asInt -> {
                ItemImageBinding.bind(holder.itemView).apply {
                    imageRecycler.apply {
                        setRecycledViewPool(imageViewPool)
                        if (widget.cols > 0) {
                            layoutManager = GridLayoutManager(context, widget.cols)
                            adapter = ImageWidget(listener).apply { setData(widget) }
                        }
                    }
                }
            }
            NamshiWidget.Type.slider.asInt -> {
                ItemSliderBinding.bind(holder.itemView).apply {
                    sliderRecycler.apply {
                        setRecycledViewPool(sliderViewPool)
                        if (onFlingListener == null)
                            PagerSnapHelper().attachToRecyclerView(this)
                        adapter = SliderWidget(fragment, listener).apply { setData(widget.images) }
                    }
                }
            }
            NamshiWidget.Type.carousel.asInt -> {
                ItemCarouselBinding.bind(holder.itemView).apply {

                    root.layoutParams.apply {
                        height = widget.heightPx
                    }

                    if (widget.images.isEmpty()) {
                        carouselLoading.show()
                    } else {
                        carouselLoading.gone()
                        carouselTitle.text = widget.title
                        carouselRecycler.setRecycledViewPool(carouselViewPool)
                        carouselRecycler.adapter =
                            CarouselWidget(listener).apply { setData(widget.images) }
                    }
                }
            }
            else -> Unit
        }
    }

    override fun getItemViewType(position: Int) = items[position].type.asInt
    override fun getItemCount() = items.size


    fun setData(data: List<NamshiWidget>) {
        items.clearAndAddAll(data)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}