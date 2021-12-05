package com.namshi.customer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.namshi.customer.base.BaseFragment
import com.namshi.customer.model.Image
import com.namshi.customer.databinding.FragmentGridProductBinding
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.ui.main.MainViewModel
import com.namshi.customer.network.response.NamshiResponse
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.ui.details.adapters.DetailsAdapter
import com.namshi.customer.utils.ShowImage
import com.namshi.customer.utils.showIf

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class DetailsFragment : BaseFragment(), ClickListener {

    companion object {
        fun newInstance(): DetailsFragment {
            val args = Bundle()
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val screenTitle: String
        get() = "ProductGridFragment"

    private var _binding: FragmentGridProductBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: DetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProductList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGridProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gridRecycler.adapter = DetailsAdapter(this@DetailsFragment).also { adapter = it }
        binding.gridRefresh.setOnRefreshListener { viewModel.refreshProductScreen() }
        binding.errorLayout.setOnClickListener { viewModel.refreshProductScreen() }
        viewModel.productListLiveData.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: Image) {
        ShowImage.showImage(requireContext(), image.url)
    }

    private fun setData(response: NamshiResponse<CarouselContent>) {
        val data = response.data
        binding.gridRefresh.isRefreshing = response.isLoading && data == null
        binding.errorLayout.showIf(response.exception != null && data == null)
        if (data != null)
            adapter.setData(data.images)
        else
            adapter.setData(listOf())
    }
}