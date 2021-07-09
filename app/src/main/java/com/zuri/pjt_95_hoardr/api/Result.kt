package com.zuri.pjt_95_hoardr.api

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 09-Jul-21 at 2:19 AM
 *
 * Represents the result of an API call
 */
sealed class Result<out T>{
    /**
     * No result has been received
     * */
    object Loading : Result<Nothing>()
    /**
     * The call was a success
     * @param data The data that is to be returned in a successful call event
     * @param message A message describing the event
     * */
    data class Success<T>(val data: T, val message: String): Result<T>()
    /**
     * The call failed
     * @param error The exception thrown on a failed call event
     * */
    data class Failure(val error: Throwable): Result<Nothing>()
}