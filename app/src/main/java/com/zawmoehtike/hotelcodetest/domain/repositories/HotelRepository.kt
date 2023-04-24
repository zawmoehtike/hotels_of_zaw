package com.zawmoehtike.hotelcodetest.domain.repositories

import com.zawmoehtike.hotelcodetest.data.remote.dto.LoginDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.OTPVerifyDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.RegisterOTPResendDto
import com.zawmoehtike.hotelcodetest.domain.models.HotelDetailsModel
import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel

interface HotelRepository {

    /*
    phoneNumber: String,
        countryCode: String,
        locale: String
     */
    suspend fun login(
        phoneNumber: String,
        countryCode: String,
        locale: String
    ): LoginDto

    suspend fun register(
        phoneNumber: String,
        name: String,
        email: String,
        countryCode: String
    ): RegisterOTPResendDto

    suspend fun resendOTP(
        time: String,
        phoneNumber: String,
        countryCode: String
    ): RegisterOTPResendDto

    suspend fun verifyOTP(
        phoneNumber: String,
        countryCode: String,
        otp: String
    ): OTPVerifyDto

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