package com.namshi.customer.ui.main

import android.os.Bundle
import com.namshi.customer.base.BaseActivity
import com.namshi.customer.databinding.ActivityFirstBinding


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        addFragment(MainFragment.newInstance(), replace = true, addToBackStack = false)
    }
}