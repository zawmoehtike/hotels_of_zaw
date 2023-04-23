package com.zawmoehtike.hotelcodetest.domain.models

data class HotelDetailsModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val reviewCount: String,
    val priceRange: String,
    val checkInDate: String,
    val checkOutDate: String,
    val popularityScore: String,
    val facilitiesList: List<String>
)