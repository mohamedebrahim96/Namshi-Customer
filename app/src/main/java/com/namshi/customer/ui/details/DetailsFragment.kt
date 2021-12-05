package com.namshi.customer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.namshi.customer.R
import com.namshi.customer.databinding.FragmentDetailsBinding
import com.namshi.customer.model.Image
import com.namshi.customer.ui.details.adapters.DetailsAdapter
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.ShowImage
import com.skydoves.bindables.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>(R.layout.fragment_details),
    ClickListener {


    private val detailViewModel: DetailViewModel by activityViewModels()
    private val detailsAdapter =
        DetailsAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            vm = detailViewModel
            adapter = detailsAdapter
        }.root
    }

    override fun onItemClick(image: Image) {
        ShowImage.showImage(requireContext(), image.url)
    }
}