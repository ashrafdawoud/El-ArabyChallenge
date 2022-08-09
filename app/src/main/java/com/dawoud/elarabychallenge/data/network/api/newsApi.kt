package com.dawoud.elarabychallenge.data.network.api

import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface newsApi {

    @GET("top-headlines?country={country}&apiKey={apiKey}")
    suspend fun getCountryNewsApi( @Path(value = "country")  country:String ,@Path(value = "apiKey")  apiKey:String) : Response<GeneralNewsDto>
    @GET("top-headlines?sources={sources}&apiKey={apiKey}")
    suspend fun getSourceNewsApi( @Path(value = "sources")  sources:String ,@Path(value = "apiKey")  apiKey:String) : Response<GeneralNewsDto>

}