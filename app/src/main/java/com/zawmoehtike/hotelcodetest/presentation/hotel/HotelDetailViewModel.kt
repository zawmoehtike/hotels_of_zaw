package com.zawmoehtike.hotelcodetest.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.models.HotelDetailsModel
import com.zawmoehtike.hotelcodetest.domain.usecases.GetHotelDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailViewModel @Inject constructor(
    private val getHotelDetails: GetHotelDetails,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var getHotelDetailsParams: GetHotelDetailsParams = GetHotelDetailsParams()

    fun getHotelDetails(result: (ViewState<HotelDetailsModel>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = getHotelDetails.invoke(
                    hotelId = getHotelDetailsParams.hotelId,
                    channel = getHotelDetailsParams.channelId,
                    checkInDate = "2023-04-25", //getHotelDetailsParams.checkInDate,
                    checkOutDate = "2023-04-26", //getHotelDetailsParams.checkOutDate
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class GetHotelDetailsParams(
        var hotelId: String = "",
        var channelId: String = "mobile",
        var checkInDate: String = "",
        var checkOutDate: String = ""
    )
}