package com.gilbersoncampos.testeaiko.data.remote.datasource

import android.util.Log
import com.gilbersoncampos.testeaiko.BuildConfig
import com.gilbersoncampos.testeaiko.data.remote.retrofit.ApiService
import javax.inject.Inject

class ApiDataSourceImpl @Inject constructor(private val service: ApiService) : ApiDataSource {
    override suspend fun searchBusStopByTerm() {
        try {
            login()
            val call = service.searchBusStopByTerm(searchTerms = "Afonso", token = TOKEN)
            if (call.isSuccessful) {
                Log.d("busstop", "${call.body()}")
            } else {
                Log.d("busstop", "ERROR")
            }

        } catch (ex: Exception) {
            Log.d("busstop", "ERROR ${ex.message}")
        }
    }
    private suspend fun login(){
        try {
            val call= service.login(TOKEN)
            if(call.isSuccessful){
                Log.d("busstop","Logado")
            }
        }catch (e:Exception){
            Log.d("busstop","Error Login")
        }
    }

    companion object {
        private const val TOKEN = BuildConfig.TOKEN
    }
}