package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class GetHotelsList @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        lat: String,
        long: String,
        provinceId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String,
        sdfsdf: String
    ) : List<HotelModel> {
        return hotelRepository.getHotelsList(lat, long, provinceId, channel, checkInDate, checkOutDate, sdfsdf)
    }
}