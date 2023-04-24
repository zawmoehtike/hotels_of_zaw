package com.zawmoehtike.hotelcodetest.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.usecases.PostLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    private val postLogin: PostLogin,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var postLoginParams = PostLoginParams()

    fun postLogin(result: (ViewState<String>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = postLogin.invoke(
                    phoneNumber = postLoginParams.phoneNumber,
                    countryCode = postLoginParams.countryCode,
                    locale = postLoginParams.locale
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class PostLoginParams(
        var phoneNumber: String = "",
        var countryCode: String = "",
        var locale: String = ""
    )
}