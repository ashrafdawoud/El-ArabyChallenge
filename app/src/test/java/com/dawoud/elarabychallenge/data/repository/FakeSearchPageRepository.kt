package com.dawoud.elarabychallenge.data.repository

import com.dawoud.elarabychallenge.data.ProvideFakeData
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.domain.repository.SearchPageGetAway
import okhttp3.ResponseBody
import retrofit2.Response

class FakeSearchPageRepository :SearchPageGetAway {

    override suspend fun search(query: String, apikey: String): Response<GeneralNewsDto> {
       if (apikey.isEmpty()){
           return Response.error(401, ResponseBody.create(null,"failed request"))
       }else if (query.isEmpty()){
           return Response.success(GeneralNewsDto("ok","0", listOf()))
       }else{
           return Response.success(ProvideFakeData.getFakeGeneralNewsDto())
       }
    }
}