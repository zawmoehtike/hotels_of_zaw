package com.zawmoehtike.hotelcodetest.data.repositories

import com.zawmoehtike.hotelcodetest.data.remote.datasources.HotelNetworkDataSource
import com.zawmoehtike.hotelcodetest.domain.models.HotelDetailsModel
import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel
import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val hotelNetworkDataSource: HotelNetworkDataSource
): HotelRepository {

    override suspend fun getHotelsList(
        lat: String,
        long: String,
        provinceId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String,
        sdfsdf: String
    ): List<HotelModel> {
        val data = hotelNetworkDataSource.getHotelsList(
            lat, long, provinceId, channel, checkInDate, checkOutDate, sdfsdf
        ).map {
            HotelModel(
                id = (it.hotelsId?:0).toString(),
                name = it.hotelName?:"",
                description = it.city.toString().capitalize() + " • " + it.cityCenterDistance.toString(),
                priceRange = it.priceRange?:"",
                reviewCount = (it.reviews?:0).toString(),
                imageUrl = it.imageCoverUrl?:""
            )
        }

        return data
    }

    override suspend fun getLocationsList(
        lat: String,
        long: String
    ): List<LocationModel> {
        val data = hotelNetworkDataSource.getLocationsList(
            lat, long
        ).map {
            LocationModel(
                id = (it.id?:0).toString(),
                title = it.name?:"",
                subTitle = it.country?:""
            )
        }

        return data
    }

    override suspend fun getHotelDetails(
        hotelId: String,
        channel: String,
        checkInDate: String,
        checkOutDate: String
    ): HotelDetailsModel {
        val data = hotelNetworkDataSource.getHotelDetails(
            hotelId, channel, checkInDate, checkOutDate
        )

        return HotelDetailsModel(
            id = (data.hotelsId?:0).toString()
        )
    }

}