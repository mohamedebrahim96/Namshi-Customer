package com.namshi.customer.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.namshi.customer.R
import com.namshi.customer.databinding.ActivityMainBinding
import com.namshi.customer.ui.details.DetailActivity
import com.namshi.customer.ui.main.adapter.HomeAdapter
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @get:VisibleForTesting
    internal val viewModel: MainViewModel by viewModels()
    private val homeAdapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            adapter = homeAdapter
            vm = viewModel
        }
    }

    fun onItemClick() {
        startActivity(Intent(this, DetailActivity::class.java))
    }
}