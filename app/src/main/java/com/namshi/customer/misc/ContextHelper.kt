package com.namshi.customer.misc

import android.content.Context
import androidx.annotation.StringRes
import com.namshi.customer.NamshiApp


/**
 * String utility functions to access string res
 * */
fun string(@StringRes id: Int, context: Context? = null): String {
    return context?.resources?.getString(id) ?: NamshiApp.instance.resources.getString(id)
}