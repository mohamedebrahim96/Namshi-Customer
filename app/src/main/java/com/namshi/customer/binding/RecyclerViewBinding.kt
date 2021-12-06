package com.namshi.customer.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.ui.main.adapters.MainWidget
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.whatif.whatIfNotNullAs


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("submitList")
    fun bindSubmitList(view: RecyclerView, itemList: List<Any>?) {
        view.adapter.whatIfNotNullAs<BindingListAdapter<Any, *>> { adapter ->
            adapter.submitList(itemList)
        }
    }

    @JvmStatic
    @BindingAdapter("submitHomeList")
    fun bindsubmitHomeList(view: RecyclerView, itemList: List<NamshiWidget>) {
        view.adapter.whatIfNotNullAs<MainWidget> { adapter ->
            adapter.setData(itemList)
        }
    }


}