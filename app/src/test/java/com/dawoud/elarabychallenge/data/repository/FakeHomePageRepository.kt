package com.dawoud.elarabychallenge.data.repository

import com.dawoud.elarabychallenge.data.ProvideFakeData
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toCountryEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toPopularEntity
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.data.network.dto.NewsDto
import com.dawoud.elarabychallenge.data.network.mapper.toModel
import com.dawoud.elarabychallenge.domain.repository.HomePageGetAway
import okhttp3.ResponseBody
import retrofit2.Response

class FakeHomePageRepository : HomePageGetAway {
    val countrynewslist = ArrayList<CountryNewsEntity>()
    val popularNewslist = ArrayList<PopularNewsEntity>()
    override suspend fun getCountryNewsNetwork(
        country: String,
        token: String
    ): Response<GeneralNewsDto>  {
        if (country.isEmpty()||token.isEmpty()){
            return Response.error(401 ,  ResponseBody.create(null,"no data found"))
        }else{
            for (item in ProvideFakeData.getFakeGeneralNewsDto().articles!!)
                setCountryNewsCache(item.toModel().toCountryEntity())
            return Response.success(ProvideFakeData.getFakeGeneralNewsDto())
        }
    }

    override suspend fun getPopularNewsNetwork(
        source: String,
        token: String
    ): Response<GeneralNewsDto> {
        if (token.isEmpty()){
            return Response.error(401 ,  ResponseBody.create(null,"no data found"))
        }else{
            for (item in ProvideFakeData.getFakeGeneralNewsDto().articles!!)
                setPopularNewsCache(item.toModel().toPopularEntity())
            return Response.success(ProvideFakeData.getFakeGeneralNewsDto())
        }    }

    override suspend fun getpopularNewsCache(): List<PopularNewsEntity> {
        return popularNewslist
    }

    override suspend fun getCountryNewsCache(): List<CountryNewsEntity> {
        return countrynewslist
    }

    override suspend fun setCountryNewsCache(countryNewsEntity: CountryNewsEntity): Long {
        countrynewslist.add(countryNewsEntity)
        return 1
    }

    override suspend fun setPopularNewsCache(popularNewsEntity: PopularNewsEntity): Long {
        popularNewslist.add(popularNewsEntity)
        return 1
    }

    override suspend fun DeletePopularNewsTable() {
        popularNewslist.clear()
    }

    override suspend fun DeleteCountryNewsTable() {
        countrynewslist.clear()
    }
}