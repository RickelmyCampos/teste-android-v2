package com.gilbersoncampos.testeaiko.useCase

import com.gilbersoncampos.testeaiko.data.model.BusStopModel
import com.gilbersoncampos.testeaiko.data.repository.BusStopRepository
import javax.inject.Inject

class SearchBusStopByTermUseCase @Inject constructor(private val repository: BusStopRepository) {
    suspend operator fun invoke(searchTerms: String):List<BusStopModel> {
       return repository.searchBusStopByTerms(searchTerms)
    }
}