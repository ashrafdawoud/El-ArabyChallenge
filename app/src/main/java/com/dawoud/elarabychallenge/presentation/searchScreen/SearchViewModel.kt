package com.dawoud.elarabychallenge.presentation.searchScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.model.searchScreen.SearchRequist
import com.dawoud.elarabychallenge.domain.usecase.searchScreen.SearchUseCase
import com.dawoud.elarabychallenge.domain.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase:SearchUseCase
) :ViewModel(){
    private val _NewsModelDataSet: MutableLiveData<Resource<List<NewsModel>>> = MutableLiveData()
    val NewsModelDataSet: LiveData<Resource<List<NewsModel>>>
        get() = _NewsModelDataSet
    fun search(searchRequist: SearchRequist){
        viewModelScope.launch {
            searchUseCase.invoke(searchRequist , Constant.apiKey)
                .onEach {
                    _NewsModelDataSet.value = it
                }.launchIn(viewModelScope)
        }
    }
}