package com.dawoud.elarabychallenge.domain.model

import java.io.Serializable
data class GeneralNewsModel(
    val status: String?,
    val totalResults :String?,
    val articles:List<NewsModel>?
)



data class SourceModel(
    val id:String?,
    val name:String?,
)
