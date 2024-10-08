package com.gilbersoncampos.testeaiko.data.repository

import com.gilbersoncampos.testeaiko.data.mappers.toModel
import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.data.remote.datasource.ApiDataSource
import com.gilbersoncampos.testeaiko.data.remote.dto.BusStopResponseDto
import com.gilbersoncampos.testeaiko.exception.CustomException
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

interface BusStopRepository {
    suspend fun searchBusStopByTerms(searchTerms: String): List<BusStopModel>
}

class BusStopRepositoryImpl @Inject constructor(private val dataSource: ApiDataSource) :
    BusStopRepository {
    override suspend fun searchBusStopByTerms(searchTerms: String): List<BusStopModel> {
        val response = dataSource.searchBusStopByTerm(searchTerms)
        return response.map { it.toModel() }
    }

}


