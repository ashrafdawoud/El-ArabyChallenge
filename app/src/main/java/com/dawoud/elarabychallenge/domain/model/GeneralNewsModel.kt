package com.dawoud.elarabychallenge.domain.model

import com.dawoud.elarabychallenge.data.network.dto.NewsDto
import com.dawoud.elarabychallenge.data.network.dto.SourceDto

data class GeneralNewsModel(
    val status: String?,
    val totalResults :String?,
    val articles:List<NewsModel>?
)
data class NewsModel(
    val source: SourceModel?,
    val author:String?,
    val title:String?,
    val description:String?,
    val url:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val content:String?,
)
data class SourceModel(
    val id:String?,
    val name:String?,

    )
