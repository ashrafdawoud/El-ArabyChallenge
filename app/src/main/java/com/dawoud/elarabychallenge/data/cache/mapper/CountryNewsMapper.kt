package com.dawoud.elarabychallenge.data.cache.mapper

import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.data.cache.entity.SourceEntity
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.model.SourceModel

fun List<CountryNewsEntity>.toListModel():List<NewsModel>{
    return this.map { it.toModel() }
}
fun CountryNewsEntity.toModel(): NewsModel {
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

fun NewsModel.toCountryEntity(): CountryNewsEntity {
    return CountryNewsEntity(
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