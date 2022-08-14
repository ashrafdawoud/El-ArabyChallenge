package com.dawoud.elarabychallenge.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetPopularNewsUseCase
import com.dawoud.elarabychallenge.domain.utils.ApplicationMode
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getCountryNewsUseCase: GetPopularNewsUseCase
    @Inject
    lateinit var applicationMode: ApplicationMode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applicationMode.excuteDesign(this)

        val pref: SharedPreferences =getSharedPreferences(
            getString(R.string.generalshared),
            AppCompatActivity.MODE_PRIVATE
        )

        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

    }
    fun switchMode(){
        applicationMode.excuteDesign(this)
    }


}