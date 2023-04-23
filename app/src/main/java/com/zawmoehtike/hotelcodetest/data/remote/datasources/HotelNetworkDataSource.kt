package com.zawmoehtike.hotelcodetest.data.remote.datasources

import com.zawmoehtike.hotelcodetest.data.remote.HotelApi
import com.zawmoehtike.hotelcodetest.data.remote.dto.GetHotelDetailsDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.GetHotelsListDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.GetLocationsListDto
import com.zawmoehtike.hotelcodetest.data.remote.utils.getBodyOrThrowNetworkException
import javax.inject.Inject

class HotelNetworkDataSource @Inject constructor(
    private val hotelApi: HotelApi
) {
    suspend fun getLocationsList(lat: String, long: String): List<GetLocationsListDto.Data> {
        val response = hotelApi.getLocationsList(lat = lat, long = long).getBodyOrThrowNetworkException()
        return response.data?: emptyList()
    }

    suspend fun getHotelsList(
        lat: String,
        long: String,
        provinceId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String,
        sdfsdf: String
    ): List<GetHotelsListDto.Data> {
        val response = hotelApi.getHotelsList(
            lat = lat,
            long = long,
            provincesId = provinceId,
            channel = channel,
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
            sdfsdf = sdfsdf
        ).getBodyOrThrowNetworkException()
        return response.data?: emptyList()
    }

    suspend fun getHotelDetails(
        hotelId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String
    ): GetHotelDetailsDto {
        val response = hotelApi.getHotelDetails(
            hotelsId = hotelId,
            channel = channel,
            checkInDate = checkInDate,
            checkOutDate = checkOutDate
        ).getBodyOrThrowNetworkException()
        return response
    }
}