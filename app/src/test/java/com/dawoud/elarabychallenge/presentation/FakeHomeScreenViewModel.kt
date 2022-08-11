package com.dawoud.elarabychallenge.presentation

import com.dawoud.domain.utils.Resource
import com.dawoud.elarabychallenge.data.cache.mapper.toListModel
import com.dawoud.elarabychallenge.data.repository.FakeHomePageRepository
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetCountryNewsUseCase
import com.dawoud.elarabychallenge.domain.usecase.homepage.GetPopularNewsUseCase
import com.dawoud.elarabychallenge.domain.utils.Constant
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response


/**
 * getCountryNews()
 *      1- call method with empty country field -> return error
 *      2- call method with empty token field  -> return error
 *      3- call method with not empty country or token - >return sucsess
 * getPopularNews()
 *      2- call method with empty token field  -> return error
 *      3- call method with not empty country or token - >return sucsess
 * */
class FakeHomeScreenViewModel {
    lateinit var countryNewsUseCase: GetCountryNewsUseCase
    lateinit var popularNewsUseCase: GetPopularNewsUseCase
    lateinit var repository: FakeHomePageRepository

    @Before
    fun setup() {
        repository = FakeHomePageRepository()
        countryNewsUseCase = GetCountryNewsUseCase(repository)
        popularNewsUseCase = GetPopularNewsUseCase(repository)
    }

    @Test
    fun `call getCountryNews() method with empty country field`() = runBlocking {
        countryNewsUseCase.invoke("", "123").collect {
            when (it) {
                is Resource.Error -> {
                    assertEquals(
                        it,
                        Resource.Error(
                            "no data found",
                            repository.getCountryNewsCache().toListModel()
                        )
                    )
                }
            }
        }
    }

    @Test
    fun `call getCountryNews() method with empty token field`() = runBlocking {
        countryNewsUseCase.invoke("eg", "").collect {
            when (it) {
                is Resource.Error -> {
                    assertEquals(
                        it,
                        Resource.Error(
                            "no data found",
                            repository.getCountryNewsCache().toListModel()
                        )
                    )
                }
            }
        }
    }

    @Test
    fun `call getCountryNews() method with not empty country or token`() = runBlocking {
        countryNewsUseCase.invoke(Constant.egyptCountry, Constant.apiKey).collect {
            when (it) {
                is Resource.Success -> {
                    assertFalse(it.data.isEmpty())
                }
            }
        }
    }

    @Test
    fun `call getPopularNews() method with empty token field`() = runBlocking {
        popularNewsUseCase.invoke("").collect {
            when (it) {
                is Resource.Error -> {
                    assertEquals(
                        it,
                        Resource.Error(
                            "no data found",
                            repository.getpopularNewsCache().toListModel()
                        )
                    )
                }
            }
        }
    }

    @Test
    fun `call getPopularNews() method with not empty country or token`() = runBlocking {
        popularNewsUseCase.invoke(Constant.apiKey).collect {
            when (it) {
                is Resource.Success -> {
                    assertFalse(it.data.isEmpty())
                }
            }
        }
    }
}
