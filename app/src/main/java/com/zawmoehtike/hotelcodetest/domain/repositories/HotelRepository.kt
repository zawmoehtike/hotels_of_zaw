package com.zawmoehtike.hotelcodetest.domain.repositories

import com.zawmoehtike.hotelcodetest.domain.models.HotelDetailsModel
import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel

interface HotelRepository {
    suspend fun getHotelsList(
        lat: String,
        long: String,
        provinceId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String,
        sdfsdf: String
    ): List<HotelModel>

    suspend fun getLocationsList(
        lat: String,
        long: String
    ): List<LocationModel>

    suspend fun getHotelDetails(
        hotelId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String
    ): HotelDetailsModel
}