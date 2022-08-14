package com.dawoud.elarabychallenge.presentation.DetailsScreen

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.databinding.FragmentDetailsBinding
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.presentation.HomeScreen.HomePageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    lateinit var binding: FragmentDetailsBinding
    val args: DetailsFragmentArgs by navArgs()
    val viewModel: DetailsScreenViewModel by viewModels()
    var bookMarkFlag = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        setContentView(args.newsModel!!)
        setNav()
        backPress(binding.root)
        return binding.root
    }

    fun setContentView(newsModel: NewsModel) {
        val view = (activity as AppCompatActivity?)!!
        view.setSupportActionBar(binding.toolbar)
        view.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        view.supportActionBar!!.setDisplayShowHomeEnabled(true)
        view.supportActionBar!!.setDisplayShowTitleEnabled(false)
        view.supportActionBar!!.setTitle(newsModel.title)
        Picasso.get().load(newsModel.urlToImage).into(binding.imageUrl)
        binding.secondTitle.setText(newsModel.title)
        binding.discription.setText(  newsModel.description)
        viewModel.checkIfBookMarkExsist(newsModel)
        binding.bookmark.setOnClickListener {
            if (bookMarkFlag){
                viewModel.deleteBookMarkRaw(newsModel)
            }else{
                viewModel.setNewsToBookMark(newsModel)
            }
        }
        listentoObserver()
    }
    fun listentoObserver(){
        viewModel.checkIfExsistDataSet.observe(requireActivity(), Observer {
            when(it){
                is Resource.Success->{
                    if (it.data){
                        bookMarkFlag = true
                        binding.bookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_24))
                    }
                }
            }
        })
        viewModel.insertDataSet.observe(requireActivity(), Observer {
            when(it){
                is Resource.Success ->{
                    bookMarkFlag = true
                    binding.bookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_24))

                }
            }
        })
        viewModel.deleteDataSet.observe(requireActivity(), Observer {
            when(it){
                is Resource.Success ->{
                    bookMarkFlag = false
                    binding.bookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_bookmark_border_24))

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
        navBar.visibility = View.GONE
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