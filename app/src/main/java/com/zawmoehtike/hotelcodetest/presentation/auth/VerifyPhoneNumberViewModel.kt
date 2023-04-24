package com.zawmoehtike.hotelcodetest.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.usecases.PostOTPResend
import com.zawmoehtike.hotelcodetest.domain.usecases.PostOTPVerify
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyPhoneNumberViewModel @Inject constructor(
    private val otpResend: PostOTPResend,
    private val otpVerify: PostOTPVerify,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var otpResendParams = OTPResendParams()
    var otpVerifyParams = OTPVerifyParams()

    fun resendOTP(result: (ViewState<String>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = otpResend.invoke(
                    time = otpResendParams.time,
                    phoneNumber = otpResendParams.phoneNumber,
                    countryCode = otpResendParams.countryCode
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    fun verifyOTP(result: (ViewState<String>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = otpVerify.invoke(
                    phoneNumber = otpVerifyParams.phoneNumber,
                    countryCode = otpVerifyParams.countryCode,
                    otp = otpVerifyParams.otp
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class OTPResendParams(
        var phoneNumber: String = "",
        var countryCode: String = "",
        var time: String = ""
    )

    data class OTPVerifyParams(
        var phoneNumber: String = "",
        var countryCode: String = "",
        var otp: String = ""
    )
}