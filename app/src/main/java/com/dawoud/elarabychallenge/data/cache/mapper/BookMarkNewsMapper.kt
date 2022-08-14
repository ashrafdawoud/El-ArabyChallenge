package com.dawoud.elarabychallenge.data.cache.mapper

import com.dawoud.data.cache.dao.CountryNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.BookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel

fun List<BookMarkNewsEntity>.toListModel():List<NewsModel>{
    return this.map { it.toModel() }
}
fun BookMarkNewsEntity.toModel(): NewsModel {
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

fun NewsModel.toBookMarkNewsEntity(): BookMarkNewsEntity {
    return BookMarkNewsEntity(
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