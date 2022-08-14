package com.dawoud.elarabychallenge.data.network.mapper

import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntity
import com.dawoud.elarabychallenge.data.network.dto.GeneralNewsDto
import com.dawoud.elarabychallenge.data.network.dto.NewsDto
import com.dawoud.elarabychallenge.data.network.dto.SourceDto
import com.dawoud.elarabychallenge.domain.model.GeneralNewsModel
import com.dawoud.elarabychallenge.domain.model.SourceModel
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel

fun GeneralNewsDto.toModel(): GeneralNewsModel {
    return GeneralNewsModel(
        status = status,
        totalResults = totalResults,
        articles = articles?.toListModel()
    )
}
fun List<NewsDto>.toListModel():List<NewsModel>{
    return this.map { it.toModel() }
}
fun NewsDto.toModel(): NewsModel {
    return NewsModel(
        source = source?.tomodel(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun SourceDto.tomodel(): SourceModel {
    return SourceModel(
        id = id,
        name = name
    )
}