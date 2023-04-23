package com.zawmoehtike.hotelcodetest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetHotelDetailsDto(
    @Json(name = "address_1")
    val address1: String?,
    @Json(name = "city_name")
    val cityName: String?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "default_price_range")
    val defaultPriceRange: String?,
    @Json(name = "discount_note")
    val discountNote: String?,
    @Json(name = "hotel_name")
    val hotelName: String?,
    @Json(name = "hotels_id")
    val hotelsId: Int?,
    @Json(name = "image_cover_url")
    val imageCoverUrl: String?,
    @Json(name = "is_discount")
    val isDiscount: Boolean?,
    @Json(name = "is_favorite")
    val isFavorite: Int?,
    @Json(name = "is_promote")
    val isPromote: Int?,
    @Json(name = "latitude")
    val latitude: String?,
    @Json(name = "longitude")
    val longitude: String?,
    @Json(name = "number_of_reviews")
    val numberOfReviews: Int?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "pictures")
    val pictures: List<Picture>?,
    @Json(name = "popular_facilities")
    val popularFacilities: List<PopularFacility>?,
    @Json(name = "popularity_score")
    val popularityScore: String?,
    @Json(name = "price_range")
    val priceRange: String?,
    @Json(name = "promote_label")
    val promoteLabel: Any?,
    @Json(name = "province_name")
    val provinceName: String?,
    @Json(name = "rating_avg")
    val ratingAvg: Int?,
    @Json(name = "reviews")
    val reviews: List<Review?>?,
    @Json(name = "star_hotel")
    val starHotel: String?
) {
    @JsonClass(generateAdapter = true)
    data class Picture(
        @Json(name = "caption")
        val caption: String?,
        @Json(name = "picture_url")
        val pictureUrl: String?
    )

    @JsonClass(generateAdapter = true)
    data class PopularFacility(
        @Json(name = "caption")
        val caption: String?,
        @Json(name = "icon")
        val icon: String?
    )

    @JsonClass(generateAdapter = true)
    data class Review(
        @Json(name = "country")
        val country: String?,
        @Json(name = "date")
        val date: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "rating_avg")
        val ratingAvg: String?,
        @Json(name = "reviews")
        val reviews: String?
    )
}