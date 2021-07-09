package com.zuri.pjt_95_hoardr.api.classes

import com.zuri.pjt_95_hoardr.api.models.User
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 09-Jul-21 at 1:24 AM
 */
interface AuthAPI {
    @POST("auth/signup")
    suspend fun signup(@Body user: User)

    @POST("auth/login")
    suspend fun login(@Body user: User)
}