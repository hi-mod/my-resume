package com.aaronchancey.myresume.data

import com.aaronchancey.myresume.domain.Error

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(val data: D) : Result<D, Nothing>
    data class Error<out E : com.aaronchancey.myresume.domain.Error>(val error: E, val data: String? = null) : Result<Nothing, E>
}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> = when (this) {
    is Result.Error -> Result.Error(error, data)
    is Result.Success -> Result.Success(map(data))
}

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> = map { }

inline fun <T, E : Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> = when (this) {
    is Result.Error -> this
    is Result.Success -> {
        action(data)
        this
    }
}
inline fun <T, E : Error> Result<T, E>.onError(action: (E, String?) -> Unit): Result<T, E> = when (this) {
    is Result.Error -> {
        action(error, data)
        this
    }
    is Result.Success -> this
}

typealias EmptyResult<E> = Result<Unit, E>
