package com.dawoud.elarabychallenge.domain.usecase.homepage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toCountryEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toListModel
import com.dawoud.elarabychallenge.data.network.mapper.toModel
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryNewsUseCase @Inject constructor(
    private val homePageGetAway: HomePageGetAway
) {
    suspend fun invoke(country:String , token:String): Flow<Resource<List<NewsModel>>> = flow {
        emit(Resource.Loading)
        try {
            val countryNews = homePageGetAway.getCountryNewsNetwork(country ,token)
            if (countryNews.isSuccessful) {
                countryNews.body()?.let {
                    it.articles?.let {
                        homePageGetAway.DeleteCountryNewsTable()
                        for (item in it)
                            homePageGetAway.setCountryNewsCache(item.toModel().toCountryEntity())
                    }
                }
                emit(Resource.Success(homePageGetAway.getCountryNewsCache().toListModel()))
            } else {
                emit(Resource.Error(countryNews.errorBody()!!.string() , homePageGetAway.getCountryNewsCache().toListModel()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:e.toString() , homePageGetAway.getCountryNewsCache().toListModel()))
        }
    }
}