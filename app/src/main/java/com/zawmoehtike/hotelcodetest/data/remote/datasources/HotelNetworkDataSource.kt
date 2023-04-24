package com.zawmoehtike.hotelcodetest.data.remote.datasources

import com.zawmoehtike.hotelcodetest.data.remote.HotelApi
import com.zawmoehtike.hotelcodetest.data.remote.dto.*
import com.zawmoehtike.hotelcodetest.data.remote.utils.getBodyOrThrowNetworkException
import com.zawmoehtike.hotelcodetest.data.remote.utils.toRequestBody
import javax.inject.Inject

class HotelNetworkDataSource @Inject constructor(
    private val hotelApi: HotelApi
) {
    suspend fun login(
        phoneNumber: String,
        countryCode: String,
        locale: String
    ): LoginDto {
        val response = hotelApi.login(
            phoneNumber = phoneNumber.toRequestBody(),
            countryCode =  countryCode.toRequestBody(),
            locale = locale.toRequestBody()
        ).getBodyOrThrowNetworkException()

        return response
    }

    suspend fun register(
        phoneNumber: String,
        name: String,
        email: String,
        countryCode: String
    ): RegisterOTPResendDto {
        val response = hotelApi.register(
            phoneNumber = phoneNumber.toRequestBody(),
            name = name.toRequestBody(),
            email = email.toRequestBody(),
            countryCode =  countryCode.toRequestBody()
        ).getBodyOrThrowNetworkException()

        return response
    }

    suspend fun resendOTP(
        time: String,
        phoneNumber: String,
        countryCode: String
    ): RegisterOTPResendDto {
        val response = hotelApi.resendOTP(
            time = time,
            phoneNumber = phoneNumber.toRequestBody(),
            countryCode =  countryCode.toRequestBody()
        ).getBodyOrThrowNetworkException()

        return response
    }

    suspend fun verifyOTP(
        phoneNumber: String,
        countryCode: String,
        otp: String,
    ): OTPVerifyDto {
        val response = hotelApi.verifyOTP(
            phoneNumber = phoneNumber.toRequestBody(),
            countryCode =  countryCode.toRequestBody(),
            otp = otp.toRequestBody()
        ).getBodyOrThrowNetworkException()

        return response
    }

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