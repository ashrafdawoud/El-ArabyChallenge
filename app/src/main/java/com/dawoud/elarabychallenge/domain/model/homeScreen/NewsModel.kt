package com.dawoud.elarabychallenge.domain.model.homeScreen
import java.io.Serializable
import com.dawoud.elarabychallenge.domain.model.SourceModel

data class NewsModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
) : Serializable