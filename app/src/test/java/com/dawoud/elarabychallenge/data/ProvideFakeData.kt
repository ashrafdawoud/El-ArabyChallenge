package com.dawoud.elarabychallenge.data

import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntity
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.data.network.dto.NewsDto
import com.dawoud.elarabychallenge.data.network.dto.SourceDto
import com.dawoud.elarabychallenge.domain.model.GeneralNewsModel
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.model.SourceModel

object ProvideFakeData {

    ///provide models

    fun getFakeNewsModel(): NewsModel {
        return NewsModel(
            source = SourceModel("id", "BBC"),
            author = "author",
            title = "news title",
            description = "news discription",
            url = "news url",
            urlToImage = "image url",
            publishedAt = "2022/2/12",
            content = "news contect"
        )
    }
    fun getFakeGeneralNewsModel():GeneralNewsModel{
        return GeneralNewsModel(
            status = "ok",
        totalResults = "12",
        articles = listOf(getFakeNewsModel() , getFakeNewsModel())
        )
    }

    ///provide entites

    fun getFakeCountryNewsEntity(): CountryNewsEntity {
        return CountryNewsEntity(
            source = SourceEntity("id", "BBC"),
            author = "author",
            title = "news title",
            description = "news discription",
            url = "news url",
            urlToImage = "image url",
            publishedAt = "2022/2/12",
            content = "news contect"
        )
    }
    fun getFakePopularNewsEntity(): PopularNewsEntity {
        return PopularNewsEntity(
            source = SourceEntity("id", "BBC"),
            author = "author",
            title = "news title",
            description = "news discription",
            url = "news url",
            urlToImage = "image url",
            publishedAt = "2022/2/12",
            content = "news contect"
        )
    }
    ///provide general news Dto

    fun getFakeGeneralNewsDto():GeneralNewsDto{
        return GeneralNewsDto(
            status = "ok",
            totalResults = "12",
            articles = listOf(getFakeNewsDto() , getFakeNewsDto())
        )
    }
    fun getFakeNewsDto(): NewsDto {
        return NewsDto(
            source = SourceDto("id", "BBC"),
            author = "author",
            title = "news title",
            description = "news discription",
            url = "news url",
            urlToImage = "image url",
            publishedAt = "2022/2/12",
            content = "news contect"
        )
    }
}