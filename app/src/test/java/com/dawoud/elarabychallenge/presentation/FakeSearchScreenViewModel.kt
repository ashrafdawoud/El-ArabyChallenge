package com.dawoud.elarabychallenge.presentation

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.repository.FakeSearchPageRepository
import com.dawoud.elarabychallenge.data.repository.SearchPageRepository
import com.dawoud.elarabychallenge.domain.model.searchScreen.SearchRequist
import com.dawoud.elarabychallenge.domain.repository.SearchPageGetAway
import com.dawoud.elarabychallenge.domain.usecase.searchScreen.SearchUseCase
import com.dawoud.elarabychallenge.domain.utils.Constant
import junit.framework.Assert
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * 1- if query is black -> return success with empty list
 * 2- if apikey is blank -> return error with 401 code
 * 3- else -> return success with data */
class FakeSearchScreenViewModel  {
    lateinit var searchUseCase:SearchUseCase
    lateinit var repository: SearchPageGetAway
    @Before
    fun setup(){
        repository = FakeSearchPageRepository()
        searchUseCase = SearchUseCase(repository)
    }

    @Test
    fun `if query is black return success with empty list`() = runBlocking{
        val searchRequist = SearchRequist()
        searchRequist.query = ""
        searchUseCase.invoke(searchRequist , Constant.apiKey).collect{
            when(it){
                is Resource.Success ->{
                    assertEquals(0 , it.data.size)
                }
            }
        }
    }
    @Test
    fun `if apikey is blank return error with 401 code`() = runBlocking{
        val searchRequist = SearchRequist()
        searchRequist.query = "A"
        searchUseCase.invoke(searchRequist , "").collect{
            when(it){
                is Resource.Error ->{
                    assertEquals("search failed",it.exception )
                }
            }
        }
    }
    @Test
    fun `else return success with data`() = runBlocking{
        val searchRequist = SearchRequist()
        searchRequist.query = "A"
        searchUseCase.invoke(searchRequist , Constant.apiKey).collect{
            when(it){
                is Resource.Success ->{
                    assertTrue(it.data.size >0 )
                }
            }
        }
    }

}