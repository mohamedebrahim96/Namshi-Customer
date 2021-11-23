package com.namshi.customer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.namshi.customer.base.BaseFragment
import com.namshi.customer.model.Image
import com.namshi.customer.databinding.FragmentGridProductBinding
import com.namshi.customer.utils.ActionListener
import com.namshi.customer.ui.main.MainViewModel
import com.namshi.customer.network.response.ApiResponse
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.ui.details.adapters.ProductGridAdapter
import com.namshi.customer.utils.ShowImage
import com.namshi.customer.utils.onClick
import com.namshi.customer.utils.showIf

/**
 * Displays list of Products in 2 column grid
 */
class DetailsFragment : BaseFragment(), ActionListener {

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

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: ProductGridAdapter


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
        binding.gridRecycler.adapter = ProductGridAdapter(this@DetailsFragment).also { adapter = it }
        binding.gridRefresh.setOnRefreshListener { viewModel.refreshProductScreen() }
        binding.errorLayout.onClick() { viewModel.refreshProductScreen() }
        viewModel.productListLiveData.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: Image) {
        ShowImage.showImage(requireContext(), image.url)
    }

    private fun setData(response: ApiResponse<CarouselContent>) {
        val data = response.data
        binding.gridRefresh.isRefreshing = response.isLoading && data == null
        binding.errorLayout.showIf(response.exception != null && data == null)
        if (data != null)
            adapter.setData(data.images)
        else
            adapter.setData(listOf())
    }
}