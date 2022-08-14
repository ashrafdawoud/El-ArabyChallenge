package com.dawoud.elarabychallenge.presentation.BookMarkScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.databinding.FragmentBookMarkBinding
import com.dawoud.elarabychallenge.presentation.DetailsScreen.DetailsScreenViewModel
import com.dawoud.elarabychallenge.presentation.HomeScreen.adapter.BookMarkAdapter
import com.dawoud.elarabychallenge.presentation.HomeScreen.adapter.SearchAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class BookMarkFragment : Fragment() {
  lateinit var binding:FragmentBookMarkBinding
  val viewmodel : BookMarkViewModel by viewModels()
    lateinit var adapter : BookMarkAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            com.dawoud.elarabychallenge.R.layout.fragment_book_mark,
            container,
            false)
        setNav()
        backPress(binding.root)
        setcontentview()
        return binding.root
    }
    fun setcontentview(){
        viewmodel.getAllNewsToBookMark()
        listentoObserver()
    }
    fun  listentoObserver(){
        viewmodel.savedDataSet.observe(requireActivity(), Observer {
            when(it){
                is Resource.Success ->{
                    adapter = BookMarkAdapter(requireContext(), it.data)
                    var layoutManager = LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.bookmarkrecy.let {
                        it.layoutManager = layoutManager
                        it.adapter = adapter
                        adapter.notifyDataSetChanged()
                        it.hasFixedSize()
                    }
                }
                is Resource.Error -> {
                    Snackbar.make(activity!!.findViewById(android.R.id.content), it.exception.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            binding.root.findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setNav() {
        val navBar: BottomNavigationView =
            requireActivity().findViewById(R.id.activity_main_bottom_navigation_view)
        navBar.visibility = View.VISIBLE
    }

    fun backPress(view: View) {
        try {
            requireActivity().onBackPressedDispatcher.addCallback(object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    view.findNavController().popBackStack()
                }
            })
        } catch (e: Exception) {

        }
    }
}