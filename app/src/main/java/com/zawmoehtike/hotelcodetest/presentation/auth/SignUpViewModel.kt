package com.zawmoehtike.hotelcodetest.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zawmoehtike.hotelcodetest.common.base.ViewState
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.domain.usecases.PostRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postRegister: PostRegister,
    private val exceptionToStringMapper: ExceptionToStringMapper
): ViewModel() {

    var postRegisterParams = PostRegisterParams()

    fun postRegister(result: (ViewState<String>) -> Unit) {
        viewModelScope.launch {
            result(ViewState.Loading())

            val catcher = runCatching {
                val data = postRegister.invoke(
                    phoneNumber = postRegisterParams.phoneNumber,
                    name = postRegisterParams.name,
                    email = postRegisterParams.email,
                    countryCode = postRegisterParams.countryCode
                )

                result(ViewState.Success(data))
            }

            catcher.getOrElse {
                val error = exceptionToStringMapper.map(it)
                result(ViewState.Error(error))
            }
        }
    }

    data class PostRegisterParams(
        var phoneNumber: String = "",
        var name: String = "",
        var email: String = "",
        var countryCode: String = ""
    )
}