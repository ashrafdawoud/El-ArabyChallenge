package com.dawoud.elarabychallenge.data.repository

import com.dawoud.elarabychallenge.data.cache.dao.BookMarkNewsDao
import com.dawoud.elarabychallenge.data.cache.entity.BookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.homeScreen.NewsModel
import com.dawoud.elarabychallenge.domain.repository.BookMarkGetAway

class BookMarkRepository constructor(
    private val bookMarkNewsDao: BookMarkNewsDao
) :BookMarkGetAway{
    override suspend fun insert(entity: BookMarkNewsEntity): Long = bookMarkNewsDao.insert(entity)

    override suspend fun getAllNews(): List<BookMarkNewsEntity>  = bookMarkNewsDao.getAllNews()

    override suspend fun checkIfNewExsist(newsModel: NewsModel): Boolean = bookMarkNewsDao.checkIfNewExsist(newsModel.url,newsModel.title)

    override suspend fun delete_Raw(newsModel: NewsModel) :Int = bookMarkNewsDao.delete_Raw(newsModel.url,newsModel.title)
}