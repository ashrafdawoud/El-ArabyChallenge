package com.dawoud.elarabychallenge.presentation

import android.util.Log
import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.ProvideFakeData
import com.dawoud.elarabychallenge.data.repository.FakeBookMarkRepository
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.CheckIfBookMarkExsist
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.DeleteBookMarkRaw
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.GetAllBookMark
import com.dawoud.elarabychallenge.domain.usecase.detailsPage.SetNewsToBookMark
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.math.log

/**
 * 1-checkifBookMarkExsist()
 *      1- insert element then will check if array contains element
 * 2-deleteBookMarkraw()
 *      1- delete inserted item
 * 3-GetAllBookMark()
 *      1- return all array items
 * 4-SetNewsToBookMark()
 *      1- insert item into array*/
class FakeBookMarkViewModel {
    private lateinit var checkIfBookMarkExsist: CheckIfBookMarkExsist
    private lateinit var deleteBookMarkRaw: DeleteBookMarkRaw
    private lateinit var setNewsToBookMark: SetNewsToBookMark
    private lateinit var getAllBookMark: GetAllBookMark
    private lateinit var repository: FakeBookMarkRepository

    @Before
    fun setUp() {
        repository = FakeBookMarkRepository()
        checkIfBookMarkExsist = CheckIfBookMarkExsist(repository)
        deleteBookMarkRaw = DeleteBookMarkRaw(repository)
        setNewsToBookMark = SetNewsToBookMark(repository)
        getAllBookMark = GetAllBookMark(repository)
    }

    @Test
    fun `insert item into array`() = runBlocking {
        getAllBookMark.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    assertEquals(0, it.data.size)
                }
            }
        }
         setNewsToBookMark.invoke(ProvideFakeData.getFakeNewsModel())
        getAllBookMark.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    assertEquals(0, it.data.size)
                }
            }
        }
    }
    @Test
    fun `delete inserted item`()= runBlocking{
        deleteBookMarkRaw.invoke(ProvideFakeData.getFakeNewsModel())
        getAllBookMark.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    assertEquals(0, it.data.size)
                }
            }
        }
    }
    @Test
    fun `insert element then will check if array contains element`()= runBlocking{
        setNewsToBookMark.invoke(ProvideFakeData.getFakeNewsModel())
        checkIfBookMarkExsist.invoke(ProvideFakeData.getFakeNewsModel()).collect{
            when (it) {
                is Resource.Success -> {
                    assertEquals(true , it.data)
                }
            }
        }

    }
}