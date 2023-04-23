package com.zawmoehtike.hotelcodetest.common.exception

import android.content.Context
import com.zawmoehtike.hotelcodetest.R
import com.zawmoehtike.hotelcodetest.data.remote.utils.NetworkException
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by Vincent on 11/27/19
 * modified by Zin Min Tun
 * copied and used by Zaw Moe Htike
 */
class ExceptionToStringMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionToStringMapper {
    companion object {
        private const val ERROR_CODE_400 = 400
        private const val ERROR_CODE_401 = 401
        private const val ERROR_CODE_422 = 422
        private const val ERROR_CODE_403 = 403
        private const val ERROR_CODE_404 = 404
        private const val ERROR_CODE_408 = 408
        private const val ERROR_CODE_500 = 500
    }

    override fun map(item: Throwable): String {
        return when (item) {
            is UnknownHostException -> context.getString(R.string.error_no_internet)
            is SocketTimeoutException -> context.getString(R.string.error_socket_timeout)
            is ConnectException -> context.getString(R.string.error_no_internet)
            is NetworkException -> parseNetworkError(item)
            is Exception -> item.message.toString()
            else -> context.getString(R.string.error_generic)
        }

    }


    private fun parseNetworkError(exception: NetworkException): String {
        when (exception.errorCode) {
            ERROR_CODE_400 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_401 -> {
                /** As of now,I added 401 handler in authentication interceptor.
                 * Open this comment if we want to handle in ui layer .
                 * val localIntent = Intent(HttpCheckAction.ACTION_NAME)
                .putExtra(HttpCheckAction.ACTION_EXTRA, HttpCodeToValue.V401.value)
                val isSent = localBroadcastManager.sendBroadcast(localIntent)
                Timber.i("send broadcast to inform 401 %b", isSent)**/

                return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                    ?: context.getString(
                        R.string.error_generic
                    )
            }
            ERROR_CODE_422 -> return exception.errorBody?.let { parseMessageFromErrorBodyFor422(it) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_408 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_403 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_404 -> return exception.errorBody?.let { parseMessageFromErrorBody(it) }
                ?: context.getString(R.string.error_server_404)
            ERROR_CODE_500 -> return context.getString(R.string.error_server_500)
        }

        return context.getString(R.string.error_generic)
    }

    private fun parseMessageFromErrorBody(errorBody: String): String {
        Timber.e("error body in string : $errorBody")
        return try {
            val errorBodyJson = JSONObject(errorBody)
            errorBodyJson.getString("message")
        } catch (exception: Exception) {
            Timber.e(exception)
            errorBody
        }
    }

    private fun parseMessageFromErrorBodyFor422(errorBody: String): String {
        Timber.e("error body in string : $errorBody")
        try {
            val errorBodyJson = JSONObject(errorBody)
            return errorBodyJson.getString("message") + " \n" + errorBodyJson.getString("data")
        } catch (exception: Exception) {
            Timber.e(exception)
        }

        return context.getString(R.string.error_generic)
    }

}



