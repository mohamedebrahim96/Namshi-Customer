package com.namshi.customer.utils

import com.namshi.customer.model.Image

/**
 * To Communicate between adapters/fragments
* */
interface ActionListener {
    fun onItemClick(image: Image)
}