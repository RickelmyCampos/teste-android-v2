package com.gilbersoncampos.testeaiko.data.remote.datasource

interface ApiDataSource {
   suspend fun searchBusStopByTerm()
}