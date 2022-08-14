package com.dawoud.elarabychallenge.domain.usecase.detailsPage

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toBookMarkNewsEntity
import com.dawoud.elarabychallenge.domain.model.NewsModel
import com.dawoud.elarabychallenge.domain.repository.BookMarkGetAway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteBookMarkRaw @Inject constructor(
    private val bookMarkGetAway: BookMarkGetAway
){
    suspend fun invoke(newsModel: NewsModel): Flow<Resource<Int>> = flow{
        emit(Resource.Loading)
        try {
            emit(Resource.Success(bookMarkGetAway.delete_Raw(newsModel)))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}