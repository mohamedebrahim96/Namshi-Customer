package com.namshi.customer.ui.main

import android.os.Bundle
import com.namshi.customer.R
import com.namshi.customer.databinding.ActivityMainBinding
import com.namshi.customer.utils.SetupFragmentUtil.addFragment
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {}
        initViews()
    }

    private fun initViews() {
        addFragment(this, MainFragment(), replace = true, addToBackStack = false)
    }

}