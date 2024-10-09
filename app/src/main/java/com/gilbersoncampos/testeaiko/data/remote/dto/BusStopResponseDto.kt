package com.gilbersoncampos.testeaiko.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BusStopResponseDto(
    val cp:Long,
    val np:String,
    val ed: String,
    val py: Double,
    val px: Double,
)
