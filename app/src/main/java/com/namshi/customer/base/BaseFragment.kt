package com.namshi.customer.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    abstract val screenTitle: String?

    val handler = Handler(Looper.getMainLooper())
    protected var activity: BaseActivity? = null


    override fun onAttach(context: Context) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onAttach")
        super.onAttach(context)
        activity = getActivity() as? BaseActivity
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
        context?.let { Glide.get(it).clearMemory() }
    }

    override fun onDetach() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDetach")
        activity = null
        super.onDetach()
    }

}
