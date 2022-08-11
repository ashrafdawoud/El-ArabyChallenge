package com.dawoud.elarabychallenge.data.repository

import com.dawoud.elarabychallenge.data.network.api.NewsApi
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.domain.repository.SearchPageGetAway
import retrofit2.Response

class SearchPageRepository constructor(
    private val newsApi: NewsApi
) : SearchPageGetAway {
    override suspend fun search(query:String , apiKey:String): Response<GeneralNewsDto> = newsApi.SearchApi(query , apiKey)
}