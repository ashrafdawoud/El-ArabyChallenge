package com.dawoud.elarabychallenge.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import javax.annotation.Nullable

@Entity(tableName = "BookMarkNewsTable")
data class BookMarkNewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,
    @ColumnInfo(name = "source")
    @Nullable
    @TypeConverters(SourceEntityConverters::class)
    val source:SourceEntity?,
    @ColumnInfo(name = "author")
    @Nullable
    val author:String?,
    @ColumnInfo(name = "title")
    @Nullable
    val title:String?,
    @ColumnInfo(name = "description")
    @Nullable
    val description:String?,
    @ColumnInfo(name = "url")
    @Nullable
    val url:String?,
    @ColumnInfo(name = "urlToImage")
    @Nullable
    val urlToImage:String?,
    @ColumnInfo(name = "publishedAt")
    @Nullable
    val publishedAt:String?,
    @ColumnInfo(name = "content")
    @Nullable
    val content:String?,
)