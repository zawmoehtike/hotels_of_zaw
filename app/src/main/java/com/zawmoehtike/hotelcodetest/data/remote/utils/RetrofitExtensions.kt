package com.zawmoehtike.hotelcodetest.data.remote.utils

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Response

fun <T> Response<T>.getBodyOrThrowNetworkException(): T {
    if (this.isSuccessful.not()) {
        val errorString = this.errorBody()!!.byteStream().bufferedReader().use { it.readText() }
        print("err code ${this.raw().code}")
        if (this.raw().code == 401) {
            throw NetworkException("Invalid username or password", this.raw().code)
        } else {
            throw NetworkException(errorString, this.code())
        }
    }
    if (this.code() == 402) {
        throw NoContentException()
    }
    val body = this.body() ?: throw NetworkException()

    return body
}

fun <T> Response<T>.getDataOrException() : T {
    if(this.isSuccessful.not()){
        val body = this.errorBody()?.string()
        body?.let {
            val message = JSONObject(it).getString("message")
            throw NetworkException(errorCode = this.code(), errorBody = message)
        }
    }
    val body = this.body() ?: throw NetworkException()
    return body
}

fun String.toRequestBody() : RequestBody{
    return this.toRequestBody("multipart/form-data".toMediaTypeOrNull())
}