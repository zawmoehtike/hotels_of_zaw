package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class PostRegister @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        name: String,
        email: String,
        countryCode: String
    ): String {
        return hotelRepository.register(phoneNumber, name, email, countryCode).message?:""
    }
}