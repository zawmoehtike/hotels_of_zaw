package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.models.HotelDetailsModel
import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class GetHotelDetails @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        hotelId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String
    ) : HotelDetailsModel {
        return hotelRepository.getHotelDetails(hotelId, channel, checkInDate, checkOutDate)
    }
}