package com.zawmoehtike.hotelcodetest.presentation.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.models.LocationModel
import com.zawmoehtike.hotelcodetest.domain.usecases.GetLocationsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(
    private val getLocationsList: GetLocationsList,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var getLocationsListParams: GetLocationsListParams = GetLocationsListParams()

    fun getLocationsList(result: (ViewState<List<LocationModel>>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = getLocationsList.invoke(
                    lat = getLocationsListParams.lat,
                    long = getLocationsListParams.long,
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class GetLocationsListParams(
        var lat: String = "",
        var long: String = ""
    )
}