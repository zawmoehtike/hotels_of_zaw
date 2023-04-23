package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.models.LocationModel
import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class GetLocationsList @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        lat: String,
        long: String
    ) : List<LocationModel> {
        return hotelRepository.getLocationsList(lat, long)
    }
}