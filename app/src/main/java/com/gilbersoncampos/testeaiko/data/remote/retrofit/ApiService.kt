package com.gilbersoncampos.testeaiko.data.remote.retrofit

import com.gilbersoncampos.testeaiko.BuildConfig

interface ApiService {
    companion object{
        private const val TOKEN= BuildConfig.TOKEN
    }
}