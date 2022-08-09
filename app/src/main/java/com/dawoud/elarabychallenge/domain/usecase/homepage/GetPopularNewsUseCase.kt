package com.dawoud.elarabychallenge.domain.usecase.homepage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toCountryEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toListModel
import com.dawoud.elarabychallenge.data.cache.mapper.toPopularEntity
import com.dawoud.elarabychallenge.data.network.mapper.toModel
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import com.dawoud.elarabychallenge.domain.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularNewsUseCase @Inject constructor(
    private val homePageGetAway: HomePageGetAway
) {
    suspend fun invoke(): Flow<Resource<List<NewsModel>>> = flow {
        emit(Resource.Loading)
        try {
            val BBCNews = homePageGetAway.getPopularNewsNetwork(Constant.BBCNEWS, Constant.apiKey)
            val NEXTWEBNews = homePageGetAway.getPopularNewsNetwork(Constant.NEXTWEB, Constant.apiKey)

            if (BBCNews.isSuccessful || NEXTWEBNews.isSuccessful) {

                homePageGetAway.DeleteCountryNewsTable()

                BBCNews.body()?.let {
                    it.articles?.let {
                        for (item in it)
                            homePageGetAway.setPopularNewsCache(item.toModel().toPopularEntity())
                    }
                }

                NEXTWEBNews.body()?.let {
                    it.articles?.let {
                        for (item in it)
                            homePageGetAway.setPopularNewsCache(item.toModel().toPopularEntity())
                    }
                }

                emit(Resource.Success(homePageGetAway.getpopularNewsCache().toListModel()))
            } else {
                emit(
                    Resource.Error(
                        "Response have An Error",
                        homePageGetAway.getpopularNewsCache().toListModel()
                    )
                )
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: e.toString(), homePageGetAway.getpopularNewsCache().toListModel())
            )
        }
    }
}