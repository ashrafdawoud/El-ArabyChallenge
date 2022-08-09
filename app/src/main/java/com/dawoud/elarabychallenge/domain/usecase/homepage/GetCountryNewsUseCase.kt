package com.dawoud.elarabychallenge.domain.usecase.homepage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toCountryEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toListModel
import com.dawoud.elarabychallenge.data.network.mapper.toModel
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import com.dawoud.elarabychallenge.domain.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryNewsUseCase @Inject constructor(
    private val homePageGetAway: HomePageGetAway
) {
    suspend fun invoke(): Flow<Resource<List<NewsModel>>> = flow {
        emit(Resource.Loading)
        try {
            val countryNews =
                homePageGetAway.getCountryNewsNetwork(Constant.egyptCountry, Constant.apiKey)
            if (countryNews.isSuccessful) {
                countryNews.body()?.let {
                    homePageGetAway.DeleteCountryNewsTable()
                    it.articles?.let {
                        for (item in it)
                            homePageGetAway.setCountryNewsCache(item.toModel().toCountryEntity())
                    }
                }
                emit(Resource.Success(homePageGetAway.getCountryNewsCache().toListModel()))
            } else {
                emit(Resource.Error("Response have An Error" , homePageGetAway.getCountryNewsCache().toListModel()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message?:e.toString() , homePageGetAway.getCountryNewsCache().toListModel()))
        }
    }
}