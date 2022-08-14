package com.dawoud.elarabychallenge.domain.usecase.detailsPage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toBookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.repository.BookMarkGetAway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetNewsToBookMark @Inject constructor(
    private val bookMarkGetAway: BookMarkGetAway
){
    suspend fun invoke(newsModel: NewsModel): Flow<Resource<Long>> = flow{
        emit(Resource.Loading)
        try {
            emit(Resource.Success(bookMarkGetAway.insert(newsModel.toBookMarkNewsEntity())))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}