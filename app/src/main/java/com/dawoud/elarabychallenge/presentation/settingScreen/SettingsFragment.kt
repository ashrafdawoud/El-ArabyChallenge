package com.dawoud.elarabychallenge.presentation.settingScreen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dawoud.elarabychallenge.databinding.FragmentSettingsBinding
import com.dawoud.elarabychallenge.presentation.MainActivity


class SettingsFragment : Fragment() {
    lateinit var binding :FragmentSettingsBinding
    lateinit var pref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, com.dawoud.elarabychallenge.R.layout.fragment_settings, container, false)
        val view = binding.root
        pref =activity!!.getSharedPreferences(
            getString(com.dawoud.elarabychallenge.R.string.generalshared),
            AppCompatActivity.MODE_PRIVATE
        )
        setcontentview()
        return view
    }
    fun setcontentview(){
        binding.modeSwitch.isChecked = !pref.getString("mode","light").equals("light")
        binding.modeSwitch.setOnClickListener {
            if (binding.modeSwitch.isChecked){
                pref.edit().putString("mode","night").apply()
                (activity as MainActivity?)?.let { it.switchMode() }
            }else{
                pref.edit().putString("mode","light").apply()
                (activity as MainActivity?)?.let { it.switchMode() }
            }
        }
    }

}