package com.namshi.customer.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    abstract val screenTitle: String?

    protected val subscriptions = CompositeDisposable()
    val handler = Handler(Looper.getMainLooper())
    protected var activity: BaseActivity? = null


    companion object {
    }

    override fun onAttach(context: Context) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onAttach")
        super.onAttach(context)
        activity = getActivity() as? BaseActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onStart")
        super.onStart()
    }

    override fun onResume() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onResume")
        super.onResume()
    }


    override fun onPause() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onPause")
        super.onPause()
        handler.removeCallbacksAndMessages(null)
        context?.let { Glide.get(it).clearMemory() }
    }

    override fun onStop() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDestroyView")
        super.onDestroyView()
        subscriptions.clear()
    }

    override fun onDestroy() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDestroy")
        subscriptions.dispose()
        super.onDestroy()
    }

    override fun onDetach() {
        Timber.d("Fragment State : ${this.javaClass.simpleName} : onDetach")
        activity = null
        super.onDetach()
    }


    /**
     * returns true if fragment view is created
     */
    val isViewCreated: Boolean
        get() = view != null

    /**
     * previously we are removing the fragment from backstack.
     * But activity onbackpressed provides the same functionality
     * So, we are using activity's onbackpressed
     */
    fun goBack() {
        activity?.onBackPressed()
    }


    /**
     * Returns true if fragment context is valid and its added
     */
    val isContextValid: Boolean
        get() = isAdded && context != null && view != null

    open fun showLoading() {
        handler.post {

        }
    }


    open fun hideLoading() {
        handler.post {

        }
    }



}
