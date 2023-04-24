package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class PostLogin @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(
        phoneNumber: String,
        countryCode: String,
        locale: String
    ): String {
        return hotelRepository.login(phoneNumber, countryCode, locale).route?:""
    }
}