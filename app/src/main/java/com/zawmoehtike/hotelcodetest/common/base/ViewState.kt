package com.zawmoehtike.hotelcodetest.common.base

sealed class ViewState<out T> {
    open operator fun invoke(): T? = null
    class Idle<out T> : ViewState<T>()
    class Loading<out T> : ViewState<T>()
    data class Nothing<out T>(val value: T) : ViewState<T>()
    data class Success<out T>(val value: T) : ViewState<T>()
    data class Error<out T>(/*val exception: Throwable,*/ val errorMessage: String) : ViewState<T>()
}
