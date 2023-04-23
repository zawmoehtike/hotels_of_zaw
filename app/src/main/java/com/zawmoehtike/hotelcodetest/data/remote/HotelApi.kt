package com.zawmoehtike.hotelcodetest.data.remote

import com.zawmoehtike.hotelcodetest.data.remote.dto.GetHotelDetailsDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.GetHotelsListDto
import com.zawmoehtike.hotelcodetest.data.remote.dto.GetLocationsListDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface HotelApi {

    // hotel/location?longitude=12345&latitude=12345
    @GET("hotel/location")
    suspend fun getLocationsList(
        @Query("longitude") long: String,
        @Query("latitude") lat: String
    ): Response<GetLocationsListDto>

    // hotel/searching
    // ?latitude=17.962433&longitude=102.630973
    // &provinces_id=2&channel=mobile
    // &check_in_date=2022-05-24&check_out_date=2022-05-31
    // &sdfsdf=sdf
    @GET("hotel/searching")
    suspend fun getHotelsList(
        @Query("longitude") long: String,
        @Query("latitude") lat: String,
        @Query("provinces_id") provincesId: String,
        @Query("channel") channel: String,
        @Query("check_in_date") checkInDate: String,
        @Query("check_out_date") checkOutDate: String,
        @Query("sdfsdf") sdfsdf: String
    ): Response<GetHotelsListDto>

    // hotel/show/detail
    // ?hotels_id=60
    // &channel=mobile
    // &check_in_date=2022-05-19&check_out_date=2022-05-22
    @GET("hotel/show/detail")
    suspend fun getHotelDetails(
        @Query("hotels_id") hotelsId: String,
        @Query("channel") channel: String,
        @Query("check_in_date") checkInDate: String,
        @Query("check_out_date") checkOutDate: String
    ): Response<GetHotelDetailsDto>
}