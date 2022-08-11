package com.dawoud.elarabychallenge.domain.utils

import android.app.Activity
import android.content.SharedPreferences
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.dawoud.elarabychallenge.R
import javax.inject.Inject

class ApplicationMode @Inject constructor() {

    fun excuteDesign(activity: Activity){
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val pref: SharedPreferences = activity.getSharedPreferences(
            activity.getString(R.string.generalshared),
            AppCompatActivity.MODE_PRIVATE
        )
        if (pref.getString("mode","") .equals("light")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = activity.resources.getColor(R.color.app_background_color)
            window.navigationBarColor = activity.resources.getColor(R.color.app_background_color)
        }
    }
}