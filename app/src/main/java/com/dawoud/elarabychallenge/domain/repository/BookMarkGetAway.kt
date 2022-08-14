package com.dawoud.elarabychallenge.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dawoud.elarabychallenge.data.cache.entity.BookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel

interface BookMarkGetAway {
    suspend fun insert(entity: BookMarkNewsEntity): Long

    suspend fun getAllNews(): List<BookMarkNewsEntity>

    suspend fun checkIfNewExsist(newsModel: NewsModel): Boolean

    suspend fun delete_Raw(newsModel: NewsModel):Int
}