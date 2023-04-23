package com.zawmoehtike.hotelcodetest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetHotelsListDto(
    @Json(name = "data")
    val `data`: List<Data>?
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "ccla")
        val ccla: String?,
        @Json(name = "cclo")
        val cclo: String?,
        @Json(name = "city")
        val city: String?,
        @Json(name = "city_center_distance")
        val cityCenterDistance: String?,
        @Json(name = "country")
        val country: String?,
        @Json(name = "currency")
        val currency: String?,
        @Json(name = "currency_decimal")
        val currencyDecimal: Int?,
        @Json(name = "default_price_range")
        val defaultPriceRange: String?,
        @Json(name = "discount_note")
        val discountNote: String?,
        @Json(name = "hotel_latitude")
        val hotelLatitude: String?,
        @Json(name = "hotel_longitude")
        val hotelLongitude: String?,
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
        @Json(name = "is_promotion")
        val isPromotion: Boolean?,
        @Json(name = "is_sold_out")
        val isSoldOut: Int?,
        @Json(name = "list_order")
        val listOrder: Int?,
        @Json(name = "popularity_score")
        val popularityScore: String?,
        @Json(name = "price_max")
        val priceMax: String?,
        @Json(name = "price_min")
        val priceMin: String?,
        @Json(name = "price_min_order")
        val priceMinOrder: String?,
        @Json(name = "price_range")
        val priceRange: String?,
        @Json(name = "promote_label")
        val promoteLabel: String?,
        @Json(name = "province")
        val province: String?,
        @Json(name = "rating_avg")
        val ratingAvg: Int?,
        @Json(name = "reviews")
        val reviews: Int?,
        @Json(name = "symbol")
        val symbol: String?
    )
}