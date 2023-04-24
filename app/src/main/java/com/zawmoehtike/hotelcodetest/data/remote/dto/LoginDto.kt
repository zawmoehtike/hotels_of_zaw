package com.zawmoehtike.hotelcodetest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*
{
    "route": "/signup"
}
 */
@JsonClass(generateAdapter = true)
data class LoginDto(
    // "/signup" or "/otp"
    @Json(name = "route")
    val route: String?
)