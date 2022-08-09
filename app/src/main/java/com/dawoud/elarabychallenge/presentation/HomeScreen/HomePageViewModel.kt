package com.dawoud.elarabychallenge.presentation.HomeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetCountryNewsUseCase
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetPopularNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val countryNewsUseCase: GetCountryNewsUseCase,
    private val popularNewsUseCase: GetPopularNewsUseCase
) : ViewModel() {
    val _counryNewsModelDataSet: MutableLiveData<Resource<List<NewsModel>>> = MutableLiveData()
    val counryNewsModelDataSet: LiveData<Resource<List<NewsModel>>>
        get() = _counryNewsModelDataSet
    val _popularNewsModelDataSet: MutableLiveData<Resource<List<NewsModel>>> = MutableLiveData()
    val popularNewsModelDataSet: LiveData<Resource<List<NewsModel>>>
        get() = _popularNewsModelDataSet

    fun getCountryNews() {
        viewModelScope.launch {
            countryNewsUseCase.invoke().onEach {
                _counryNewsModelDataSet.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getPopularNews() {
        viewModelScope.launch {
            countryNewsUseCase.invoke().onEach {
                _popularNewsModelDataSet.value = it
            }.launchIn(viewModelScope)
        }
    }
}