package com.dawoud.elarabychallenge.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsTable")
data class NewsEntity(
    @PrimaryKey
    val id: Int
)