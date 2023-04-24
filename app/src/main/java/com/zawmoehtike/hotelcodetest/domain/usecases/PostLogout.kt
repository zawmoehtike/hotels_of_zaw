package com.zawmoehtike.hotelcodetest.domain.usecases

import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class PostLogout @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(): String {
        return ""
    }
}