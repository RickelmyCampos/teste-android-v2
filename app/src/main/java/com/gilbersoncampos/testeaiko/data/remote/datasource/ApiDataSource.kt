package com.gilbersoncampos.testeaiko.data.remote.datasource

import com.gilbersoncampos.testeaiko.data.remote.dto.BusStopResponseDto

interface ApiDataSource {
   suspend fun searchBusStopByTerm(searchTerms:String):List<BusStopResponseDto>
}