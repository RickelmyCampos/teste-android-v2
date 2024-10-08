package com.gilbersoncampos.testeaiko.data.remote.datasource

import android.util.Log
import com.gilbersoncampos.testeaiko.BuildConfig
import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.data.remote.dto.BusStopResponseDto
import com.gilbersoncampos.testeaiko.data.remote.retrofit.ApiService
import com.gilbersoncampos.testeaiko.exception.CustomException
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(private val service: ApiService) : ApiDataSource {
    override suspend fun searchBusStopByTerm(searchTerms: String): List<BusStopResponseDto> {
        try {
            login()
            val call = service.searchBusStopByTerm(searchTerms = searchTerms, token = TOKEN)
            if (call.isSuccessful) {
                return call.body() ?: listOf()
            }
            throw CustomException.ErrorException
        } catch (ex: Exception) {
            throw CustomException.ErrorException
        }
    }

    private suspend fun login() {
        try {
            val call = service.login(TOKEN)
            if (!call.isSuccessful) {
                throw CustomException.ErrorException
            }
        } catch (e: Exception) {
            throw CustomException.ErrorException
        }
    }

    companion object {
        private const val TOKEN = BuildConfig.TOKEN
    }
}