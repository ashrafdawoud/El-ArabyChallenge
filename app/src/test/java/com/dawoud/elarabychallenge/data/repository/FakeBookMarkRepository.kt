package com.dawoud.elarabychallenge.data.repository

import com.dawoud.elarabychallenge.data.cache.entity.BookMarkNewsEntity
import com.dawoud.elarabychallenge.data.cache.mapper.toBookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.repository.BookMarkGetAway
import kotlinx.coroutines.delay

class FakeBookMarkRepository : BookMarkGetAway {
    var bookmarkDataBase = ArrayList<BookMarkNewsEntity>()

    override suspend fun insert(entity: BookMarkNewsEntity): Long {
         bookmarkDataBase.add(entity)
        delay(500)
         return 1
    }

    override suspend fun getAllNews(): List<BookMarkNewsEntity> {
        return bookmarkDataBase
    }

    override suspend fun checkIfNewExsist(newsModel: NewsModel): Boolean {
        return bookmarkDataBase.contains(newsModel.toBookMarkNewsEntity())
    }

    override suspend fun delete_Raw(newsModel: NewsModel): Int {
        for (item in bookmarkDataBase){
            if (item.url.equals(newsModel.url)&&item.title.equals(newsModel.title)){
                bookmarkDataBase.remove(item)
            }
        }
        return 1
    }
}