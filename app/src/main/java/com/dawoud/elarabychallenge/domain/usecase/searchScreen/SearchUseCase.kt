package com.dawoud.elarabychallenge.domain.usecase.searchScreen

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.network.mapper.toListModel
import com.dawoud.elarabychallenge.data.repository.SearchPageRepository
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.model.searchScreen.SearchRequist
import com.dawoud.elarabychallenge.domain.repository.SearchPageGetAway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchPageGetAway: SearchPageGetAway
) {
    suspend fun invoke(searchRequist: SearchRequist, apiKey: String): Flow<Resource<List<NewsModel>>> = flow {
        emit(Resource.Loading)
        try {
            if (!searchRequist.query.equals("")) {
                val searcmodels = searchPageGetAway.search(searchRequist.query, apiKey)
                if (searcmodels.isSuccessful){
                    searcmodels.body()?.let {
                        emit(Resource.Success(it.articles!!.toListModel()))
                    }
                }else{
                    emit(Resource.Error("search failed"))
                }
            }else{
                emit(Resource.Success(listOf()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}