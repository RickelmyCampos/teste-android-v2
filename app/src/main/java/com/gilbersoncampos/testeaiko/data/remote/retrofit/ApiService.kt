package com.gilbersoncampos.testeaiko.data.remote.retrofit

import com.gilbersoncampos.testeaiko.BuildConfig
import com.gilbersoncampos.testeaiko.data.remote.dto.BusStopResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2.1/Parada/Buscar")
    suspend fun searchBusStopByTerm(@Query("termosBusca") searchTerms:String,@Query("token") token:String):Response<List<BusStopResponseDto>>
    @POST("v2.1/Login/Autenticar")
    suspend fun login(@Query("token") token:String):Response<Boolean>
}