package com.dawoud.elarabychallenge.presentation.BookMarkScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.CheckIfBookMarkExsist
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.DeleteBookMarkRaw
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.GetAllBookMark
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.SetNewsToBookMark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel  @Inject constructor(
    private val checkIfBookMarkExsist: CheckIfBookMarkExsist,
    private val deleteBookMarkRaw: DeleteBookMarkRaw,
    private val setNewsToBookMark: SetNewsToBookMark,
    private val getAllBookMark: GetAllBookMark,
) : ViewModel() {
    private val _checkIfExsistDataSet: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val checkIfExsistDataSet: LiveData<Resource<Boolean>>
        get() = _checkIfExsistDataSet

    private val _deleteDataSet: MutableLiveData<Resource<Int>> = MutableLiveData()
    val deleteDataSet: LiveData<Resource<Int>>
        get() = _deleteDataSet

    private val _insertDataSet: MutableLiveData<Resource<Long>> = MutableLiveData()
    val insertDataSet: LiveData<Resource<Long>>
        get() = _insertDataSet
    private val _savedDataSet: MutableLiveData<Resource<List<NewsModel>>> = MutableLiveData()
    val savedDataSet: LiveData<Resource<List<NewsModel>>>
        get() = _savedDataSet

    fun checkIfBookMarkExsist(newsModel: NewsModel) {
        viewModelScope.launch {
            checkIfBookMarkExsist.invoke(newsModel)
                .onEach {
                    _checkIfExsistDataSet.value = it
                }.launchIn(viewModelScope)
        }
    }
    fun deleteBookMarkRaw(newsModel: NewsModel) {
        viewModelScope.launch {
            deleteBookMarkRaw.invoke(newsModel)
                .onEach {
                    _deleteDataSet.value = it
                }.launchIn(viewModelScope)
        }
    }
    fun setNewsToBookMark(newsModel: NewsModel) {
        viewModelScope.launch {
            setNewsToBookMark.invoke(newsModel)
                .onEach {
                    _insertDataSet.value = it
                }.launchIn(viewModelScope)
        }
    }
    fun getAllNewsToBookMark() {
        viewModelScope.launch {
            getAllBookMark.invoke()
                .onEach {
                    _savedDataSet.value = it
                }.launchIn(viewModelScope)
        }
    }

}