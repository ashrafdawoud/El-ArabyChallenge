package com.dawoud.elarabychallenge.data.cache.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.annotation.Nullable

@Entity(tableName = "PopularNewsTable")
data class PopularNewsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Nullable
    val id: Int?,
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
data class SourceEntity(
    @ColumnInfo(name = "id")
    @Nullable
    val id:String?,
    @ColumnInfo(name = "name")
    @Nullable
    val name:String?,
)
class SourceEntityConverters {
    @TypeConverter
    fun fromSourceEntity(stat: SourceEntity): String {
        return Gson().toJson(stat)
    }
    @TypeConverter
    fun toSourceEntity(json: String): SourceEntity {
        val type = object : TypeToken<SourceEntity>() {}.type
        return Gson().fromJson<SourceEntity>(json, type)
    }
}