package com.dawoud.elarabychallenge.domain.usecase.homepage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toCountryEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toListModel
import com.dawoud.elarabychallenge.data.cache.mapper.toPopularEntity
import com.dawoud.elarabychallenge.data.network.mapper.toModel
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import com.dawoud.elarabychallenge.domain.utils.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularNewsUseCase @Inject constructor(
    private val homePageGetAway: HomePageGetAway
) {
    suspend fun invoke(token:String): Flow<Resource<List<NewsModel>>> = flow {
        emit(Resource.Loading)
        try {
            val BBCNews = homePageGetAway.getPopularNewsNetwork(Constant.BBCNEWS, token)
            val NEXTWEBNews = homePageGetAway.getPopularNewsNetwork(Constant.NEXTWEB, token)

            if (BBCNews.isSuccessful || NEXTWEBNews.isSuccessful) {

                homePageGetAway.DeletePopularNewsTable()

                BBCNews.body()?.let {
                    it.articles?.let {it2->
                        for (item in it2)
                            homePageGetAway.setPopularNewsCache(item.toModel().toPopularEntity())
                    }
                }

                NEXTWEBNews.body()?.let {
                    it.articles?.let {it2->
                        for (item in it2)
                            homePageGetAway.setPopularNewsCache(item.toModel().toPopularEntity())
                    }
                }

                emit(Resource.Success(homePageGetAway.getpopularNewsCache().toListModel()))
            } else {
                emit(
                    Resource.Error(
                        BBCNews.errorBody()!!.string() ?: NEXTWEBNews.errorBody()!!.string(),
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