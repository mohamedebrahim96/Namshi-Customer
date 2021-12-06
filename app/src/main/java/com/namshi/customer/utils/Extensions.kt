package com.namshi.customer.utils

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.namshi.customer.model.Image


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
infix fun ImageView.load(image: Image?) {

    var req = Glide.with(this)
        .load(image?.url)
        .fitCenter()

    if (image != null) {
        req = req.override(image.widthPx, image.heightPx)
    }

    req.into(this)
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}


val Number.dpToPx get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

fun <E> MutableList<E>.clearAndAddAll(elements: Collection<E>) {
    this.clear()
    this.addAll(elements)
}