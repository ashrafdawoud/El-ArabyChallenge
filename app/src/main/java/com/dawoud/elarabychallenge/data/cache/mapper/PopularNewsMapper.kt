package com.dawoud.elarabychallenge.data.cache.mapper

import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.PopularNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntity
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.model.SourceModel

fun PopularNewsEntity.toModel(): NewsModel {
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

fun SourceEntity.tomodel(): SourceModel {
    return SourceModel(
        id = id,
        name = name
    )
}

fun NewsModel.toPopularEntity(): PopularNewsEntity {
    return PopularNewsEntity(
        id = 0,
        source = source?.toEntity(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun SourceModel.toEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name
    )
}