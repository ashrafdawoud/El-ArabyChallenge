package com.dawoud.elarabychallenge.domain.repository

import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.domain.model.GeneralNewsModel
import retrofit2.Response

interface HomePageGetAway {
    suspend fun getCountryNewsNetwork(country:String , token:String):Response<GeneralNewsDto>
    suspend fun getPopularNewsNetwork(source:String , token:String):Response<GeneralNewsDto>
    suspend fun getpopularNewsCache():List<PopularNewsEntity>
    suspend fun getCountryNewsCache():List<CountryNewsEntity>
    suspend fun setCountryNewsCache(countryNewsEntity:CountryNewsEntity):Long
    suspend fun setPopularNewsCache(popularNewsEntity: PopularNewsEntity):Long
    suspend fun DeletePopularNewsTable()
    suspend fun DeleteCountryNewsTable()
}