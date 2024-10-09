package com.gilbersoncampos.testeaiko.data.mappers

import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.data.remote.dto.BusStopResponseDto
import com.google.android.gms.maps.model.LatLng

fun BusStopResponseDto.toModel() =
    BusStopModel(id = cp.toString(), name = np, position = LatLng(py, px))