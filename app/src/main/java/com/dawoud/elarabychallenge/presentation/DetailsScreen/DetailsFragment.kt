package com.dawoud.elarabychallenge.presentation.DetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.databinding.FragmentDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailsFragment : Fragment() {
lateinit var binding : FragmentDetailsBinding
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
        setContentView()
        setNav()
        backPress(binding.root)
        return binding.root
    }
    fun setContentView(){
        val view = (activity as AppCompatActivity?)!!
        view.setSupportActionBar(binding.toolbar)
        view.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        view.supportActionBar!!.setDisplayShowHomeEnabled(true)
        view.supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            binding.root.findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
    fun setNav(){
        val navBar: BottomNavigationView =
            requireActivity().findViewById(R.id.activity_main_bottom_navigation_view)
        navBar.visibility = View.GONE
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