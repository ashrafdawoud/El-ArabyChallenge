package com.dawoud.elarabychallenge.presentation.HomeScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.databinding.FragmentHomeScreenBinding
import com.dawoud.elarabychallenge.presentation.HomeScreen.adapter.CountryAdapter
import com.dawoud.elarabychallenge.presentation.HomeScreen.adapter.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    lateinit var binding: FragmentHomeScreenBinding
    val viewModel: HomePageViewModel by viewModels()
    lateinit var countryAdapter: CountryAdapter
    lateinit var popularAdapter: PopularAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            com.dawoud.elarabychallenge.R.layout.fragment_home_screen,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        setContentView()
        super.onResume()
    }
    fun setContentView() {
        viewModel.getCountryNews()
        viewModel.getPopularNews()
        observeCountryNewsData()
        observePopularNewsData()
    }

    fun observeCountryNewsData() {
        viewModel.counryNewsModelDataSet.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    countryAdapter = CountryAdapter(requireContext(), it.data)
                    var layoutManager = LinearLayoutManager(context?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    binding.countryRecyclerview.let {
                        it.layoutManager = layoutManager
                        it.hasFixedSize()
                        it.adapter = countryAdapter
                    }
                    countryAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    Log.e("ee",it.exception)
                }
            }
        })
    }
    fun observePopularNewsData() {
        viewModel.popularNewsModelDataSet.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    popularAdapter = PopularAdapter(requireContext(), it.data)
                    var layoutManager = LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.popularRecyclerview.let {
                        it.layoutManager = layoutManager
                        it.hasFixedSize()
                        it.adapter = popularAdapter
                    }
                    popularAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    Log.e("ee",it.exception)
                }
            }
        })
    }

}