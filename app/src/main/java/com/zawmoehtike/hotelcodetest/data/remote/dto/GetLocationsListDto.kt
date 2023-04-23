package com.zawmoehtike.hotelcodetest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetLocationsListDto(
    @Json(name = "data")
    val `data`: List<Data>?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "active_hotels")
        val activeHotels: Int?,
        @Json(name = "country")
        val country: String?,
        @Json(name = "hotel_search_counter")
        val hotelSearchCounter: Int?,
        @Json(name = "id")
        val id: Int?,
        @Json(name = "latitude")
        val latitude: String?,
        @Json(name = "longitude")
        val longitude: String?,
        @Json(name = "name")
        val name: String?
    )
}