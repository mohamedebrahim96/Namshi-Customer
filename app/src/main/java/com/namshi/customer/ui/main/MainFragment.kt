package com.namshi.customer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.namshi.customer.R
import com.namshi.customer.databinding.FragmentMainBinding
import com.namshi.customer.model.Image
import com.namshi.customer.ui.details.DetailsFragment
import com.namshi.customer.ui.main.adapters.MainWidget
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.SetupFragmentUtil.addFragment
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(R.layout.fragment_main), ClickListener {


    @get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()
    private lateinit var mainWidgetAdapter: MainWidget

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        mainWidgetAdapter = MainWidget(this, this)
        return binding {
            adapter = mainWidgetAdapter
            vm = viewModel
        }.root
    }


    override fun onItemClick(image: Image) {
        addFragment(
            activity = requireActivity() as AppCompatActivity,
            fragment = DetailsFragment(),
            replace = true, addToBackStack = true
        )
    }
}