package com.dawoud.elarabychallenge.data.network.api

import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getCountryNewsApi(@Query(value = "country")  country:String, @Query(value = "apiKey")  apiKey:String) : Response<GeneralNewsDto>
    @GET("top-headlines")
    suspend fun getSourceNewsApi(@Query(value = "sources")  sources:String, @Query(value = "apiKey")  apiKey:String) : Response<GeneralNewsDto>
    @GET("everything")
    suspend fun SearchApi(@Query(value = "q")  q:String, @Query(value = "apiKey")  apiKey:String) : Response<GeneralNewsDto>

}