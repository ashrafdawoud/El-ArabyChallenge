package com.dawoud.elarabychallenge.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.R
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetCountryNewsUseCase
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetPopularNewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getCountryNewsUseCase: GetPopularNewsUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            getCountryNewsUseCase.invoke().collect {
               it.apply {
                    when (it) {
                    is Resource.Success ->{
                        Log.e("asasas",it.toString())
                    }
                        is Resource.Error ->{
                            Log.e("asasas",it.exception.toString())
                        }
                }}
            }
        }
    }
}