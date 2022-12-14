package com.dawoud.elarabychallenge.data.cache.mapper

import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.CountryNewsEntity
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel

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