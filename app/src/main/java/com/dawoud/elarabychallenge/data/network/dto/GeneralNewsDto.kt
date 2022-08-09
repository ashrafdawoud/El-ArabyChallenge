package com.dawoud.elarabychallenge.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

data class GeneralNewsDto(
    @SerializedName("status")
    @Expose
    @Nullable
    val status: String?,
    @SerializedName("totalResults")
    @Expose
    @Nullable
    val totalResults :String?,
    @SerializedName("articles")
    @Expose
    @Nullable
    val articles:List<NewsDto>?
)
data class NewsDto(
    @SerializedName("source")
    @Expose
    @Nullable
    val source:SourceDto?,
    @SerializedName("author")
    @Expose
    @Nullable
    val author:String?,
    @SerializedName("title")
    @Expose
    @Nullable
    val title:String?,
    @SerializedName("description")
    @Expose
    @Nullable
    val description:String?,
    @SerializedName("url")
    @Expose
    @Nullable
    val url:String?,
    @SerializedName("urlToImage")
    @Expose
    @Nullable
    val urlToImage:String?,
    @SerializedName("publishedAt")
    @Expose
    @Nullable
    val publishedAt:String?,
    @SerializedName("content")
    @Expose
    @Nullable
    val content:String?,
)
data class SourceDto(
    @SerializedName("id")
    @Expose
    @Nullable
    val id:String?,
    @SerializedName("name")
    @Expose
    @Nullable
    val name:String?,

)
