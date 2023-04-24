package com.zawmoehtike.hotelcodetest.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class OTPVerifyDto(
    // todo: to correct response body
    @Json(name = "token")
    val token: String?
)