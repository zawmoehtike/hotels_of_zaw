package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class PostOTPVerify @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        countryCode: String,
        otp: String
    ): String {
        return hotelRepository.verifyOTP(phoneNumber, countryCode, otp).token?:""
    }
}