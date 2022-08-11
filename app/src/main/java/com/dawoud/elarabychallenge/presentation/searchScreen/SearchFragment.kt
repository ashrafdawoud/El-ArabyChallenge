package com.dawoud.elarabychallenge.presentation.searchScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.databinding.FragmentSearchBinding
import com.dawoud.elarabychallenge.domain.model.searchScreen.SearchRequist
import com.dawoud.elarabychallenge.presentation.Adapter.GeneralAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    val viewmodel :SearchViewModel by viewModels()
    lateinit var binding : FragmentSearchBinding
    lateinit var generalAdapter: GeneralAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchRequist = SearchRequist()
        binding.listener = viewmodel
        setContentview()
        return binding.root//inflater.inflate(R.layout.fragment_search, container, false)
    }
    fun setContentview(){
        viewmodel.NewsModelDataSet.observe(viewLifecycleOwner , Observer {
            when(it){
                is Resource.Loading ->{}
                is Resource.Success ->{
                    generalAdapter = GeneralAdapter(requireContext(), it.data)
                    var layoutManager = LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.searchRecy.let {
                        it.layoutManager = layoutManager
                        it.hasFixedSize()
                        it.adapter = generalAdapter
                        generalAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error ->{}
            }
        })
    }

}