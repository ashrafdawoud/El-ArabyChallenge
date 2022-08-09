package com.dawoud.elarabychallenge.data.repository

import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.data.cache.dao.PopularNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.network.api.newsApi
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import com.dawoud.elarabychallenge.domain.utils.Constant
import retrofit2.Response

class HomePageRepository constructor(
    val newsApi: newsApi,
    val countryNewsDao: CountryNewsDao,
    val popularNewsDao: PopularNewsDao
): HomePageGetAway {
    override suspend fun getCountryNewsNetwork(
        country: String,
        token: String
    ): Response<GeneralNewsDto> = newsApi.getCountryNewsApi(country , token)

    override suspend fun getPopularNewsNetwork(
        source: String,
        token: String
    ): Response<GeneralNewsDto> = newsApi.getSourceNewsApi(source, token)

    override suspend fun getpopularNewsCache(): List<PopularNewsEntity> = popularNewsDao.getAllNews()

    override suspend fun getCountryNewsCache(): List<CountryNewsEntity> = countryNewsDao.getAllNews()

    override suspend fun setCountryNewsCache(countryNewsEntity: CountryNewsEntity) : Long = countryNewsDao.insert(countryNewsEntity)

    override suspend fun setPopularNewsCache(popularNewsEntity: PopularNewsEntity) :Long = popularNewsDao.insert(popularNewsEntity)

    override suspend fun DeletePopularNewsTable() = popularNewsDao.delete_table()

    override suspend fun DeleteCountryNewsTable() = countryNewsDao.delete_table()
}