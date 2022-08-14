package com.dawoud.elarabychallenge.domain.model

import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
data class GeneralNewsModel(
    val status: String?,
    val totalResults :String?,
    val articles:List<NewsModel>?
)



data class SourceModel(
    val id:String?,
    val name:String?,
)
