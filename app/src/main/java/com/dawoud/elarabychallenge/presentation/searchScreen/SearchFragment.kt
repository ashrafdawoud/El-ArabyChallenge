package com.dawoud.elarabychallenge.presentation.searchScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.databinding.FragmentSearchBinding
import com.dawoud.elarabychallenge.domain.model.searchScreen.SearchRequist
import com.dawoud.elarabychallenge.presentation.HomeScreen.adapter.SearchAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    val viewmodel :SearchViewModel by viewModels()
    lateinit var binding : FragmentSearchBinding
    lateinit var searchAdapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchRequist = SearchRequist()
        binding.listener = viewmodel
        setContentview()
        setUpNav()
        backPress(binding.root)
        return binding.root//inflater.inflate(R.layout.fragment_search, container, false)
    }
    fun setContentview(){
        viewmodel.NewsModelDataSet.observe(viewLifecycleOwner , Observer {
            when(it){
                is Resource.Loading ->{}
                is Resource.Success ->{
                    searchAdapter = SearchAdapter(requireContext(), it.data)
                    var layoutManager = LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.searchRecy.let {
                        it.layoutManager = layoutManager
                        it.adapter = searchAdapter
                        searchAdapter.notifyDataSetChanged()
                        it.hasFixedSize()
                    }
                }
                is Resource.Error ->{}
            }
        })
    }
    fun setUpNav(){
        try {
            val navBar: BottomNavigationView = requireActivity().findViewById(R.id.activity_main_bottom_navigation_view)
            navBar.visibility = View.VISIBLE
        }catch (e:Exception){}
    }
    fun backPress(view:View){
        try {
            requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    view.findNavController().popBackStack()
                }
            })
        }catch (e:Exception){

        }
    }
}