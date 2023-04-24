package com.zawmoehtike.hotelcodetest.data.remote

import com.zawmoehtike.hotelcodetest.data.remote.dto.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface HotelApi {
    @Multipart
    @POST("mobile/check")
    suspend fun login(
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("country_code") countryCode: RequestBody?,
        @Part("locale") locale: RequestBody?
    ): Response<LoginDto>

    @Multipart
    @POST("signup")
    suspend fun register(
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("name") name: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("country_code") countryCode: RequestBody?
    ): Response<RegisterOTPResendDto>

    @Multipart
    @POST("otp/resend")
    suspend fun resendOTP(
        @Query("time") time: String,
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("country_code") countryCode: RequestBody?
    ): Response<RegisterOTPResendDto>

    @Multipart
    @POST("otp/verify")
    suspend fun verifyOTP(
        @Part("phone_number") phoneNumber: RequestBody?,
        @Part("country_code") countryCode: RequestBody?,
        @Part("otp") otp: RequestBody?
    ): Response<OTPVerifyDto>

    @POST("logout")
    suspend fun logout(): Response<LogoutDto>

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