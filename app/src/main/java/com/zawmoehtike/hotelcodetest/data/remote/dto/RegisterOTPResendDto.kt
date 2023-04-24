package com.zawmoehtike.hotelcodetest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterOTPResendDto(
    @Json(name = "message")
    val message: String?
)