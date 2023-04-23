package com.zawmoehtike.hotelcodetest.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.models.HotelModel
import com.zawmoehtike.hotelcodetest.domain.usecases.GetHotelsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelListViewModel @Inject constructor(
    private val getHotelsList: GetHotelsList,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var getHotelsListParams: GetHotelsListParams = GetHotelsListParams()

    fun getHotelsList(result: (ViewState<List<HotelModel>>) -> Unit) {
        viewModelScope.launch {
            val catcher = runCatching {
                val data = getHotelsList.invoke(
                    lat = getHotelsListParams.lat,
                    long = getHotelsListParams.long,
                    provinceId = "2", //getHotelsListParams.provinceId,
                    channel = "mobile",
                    checkInDate = "2023-04-25", //getHotelsListParams.checkInDate,
                    checkOutDate = "2023-04-26", //getHotelsListParams.checkOutDate,
                    sdfsdf = ""
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class GetHotelsListParams(
        var lat: String = "",
        var long: String = "",
        var provinceId: String = "",
        var channel: String = "",
        var checkInDate: String = "",
        var checkOutDate: String = ""
    )
}