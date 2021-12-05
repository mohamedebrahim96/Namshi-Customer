package com.namshi.customer.ui.main

import android.os.Bundle
import com.namshi.customer.base.BaseActivity
import com.namshi.customer.databinding.ActivityMainBinding

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        addFragment(MainFragment.newInstance(), replace = true, addToBackStack = false)
    }
}