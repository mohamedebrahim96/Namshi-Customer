package com.namshi.customer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.namshi.customer.base.BaseFragment
import com.namshi.customer.databinding.FragmentMainBinding
import com.namshi.customer.model.Image
import com.namshi.customer.network.response.NamshiResponse
import com.namshi.customer.network.response.HomeContent
import com.namshi.customer.ui.details.DetailsFragment
import com.namshi.customer.ui.main.adapters.MainWidget
import com.namshi.customer.utils.ClickListener
import com.namshi.customer.utils.showIf

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class MainFragment : BaseFragment(), ClickListener {


    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override val screenTitle: String
        get() = "Home Screen"

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: MainWidget

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainWidget(this, this)
        binding.mainRecycler.adapter = adapter
        binding.mainRefresh.setOnRefreshListener { viewModel.refreshMainScreen() }
        binding.errorLayout.setOnClickListener { viewModel.refreshMainScreen() }
        viewModel.homeContentLiveData.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData(response: NamshiResponse<HomeContent>) {
        val data = response.data

        binding.mainRefresh.isRefreshing = response.isLoading
        binding.errorLayout.showIf(response.exception != null && data == null)

        if (data != null)
            adapter.setData(data.content)
        else
            adapter.setData(listOf())
    }

    override fun onItemClick(image: Image) {
        activity?.addFragment(DetailsFragment.newInstance(), true, addToBackStack = true)
    }
}