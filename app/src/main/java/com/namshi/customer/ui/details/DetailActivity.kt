package com.namshi.customer.ui.details

import android.os.Bundle
import com.namshi.customer.R
import com.namshi.customer.databinding.ActivityDetailBinding
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
        }
    }

}
