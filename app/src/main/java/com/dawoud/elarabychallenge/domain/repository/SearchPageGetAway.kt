package com.dawoud.elarabychallenge.domain.repository

import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import retrofit2.Response

interface SearchPageGetAway {
    suspend fun search(query:String , apikey:String):Response<GeneralNewsDto>
}