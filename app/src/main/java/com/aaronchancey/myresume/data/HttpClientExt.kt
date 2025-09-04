package com.aaronchancey.myresume.data

import android.util.Log
import com.aaronchancey.myresume.domain.DataError
import com.aaronchancey.myresume.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlin.coroutines.coroutineContext
import kotlinx.coroutines.ensureActive

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        Log.e("", e.toString(), e)
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        Log.e("", e.toString(), e)
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        Log.e("", e.toString(), e)
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse,
): Result<T, DataError.Remote> {
    Log.d("", "Response: $response")
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                println(e)
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        400 -> Result.Error(DataError.Remote.BAD_REQUEST, response.body())
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT, response.body())
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS, response.body())
        in 500..599 -> Result.Error(DataError.Remote.SERVER, response.body())
        else -> Result.Error(DataError.Remote.UNKNOWN, response.body())
    }
}
