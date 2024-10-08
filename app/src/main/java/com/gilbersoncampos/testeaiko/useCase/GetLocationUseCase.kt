package com.gilbersoncampos.testeaiko.useCase

import com.gilbersoncampos.testeaiko.services.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val locationService: LocationService) {
    operator fun invoke():Flow<LatLng?> = locationService.requestLocationUpdates()
}