package domain.usecase.location

import com.jarroyo.firstkotlinmultiplatform.data.LocationModel
import com.regin.startmultiplatform.LocationRepository
import domain.Response
import domain.usecase.base.BaseUseCase

open class GetLocationMPPListUseCase(val repository: LocationRepository) : BaseUseCase<Nothing, List<LocationModel>>() {

    override suspend fun run(): Response<List<LocationModel>> {
        return repository.getLocationList()
    }
}