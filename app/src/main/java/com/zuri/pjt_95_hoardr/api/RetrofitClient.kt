package com.zuri.pjt_95_hoardr.api

import com.zuri.pjt_95_hoardr.api.classes.AuthAPI
import com.zuri.pjt_95_hoardr.api.classes.ItemsAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 09-Jul-21 at 1:21 AM
 */
class RetrofitClient {
    private val REQUEST_TIMEOUT = 60L;

    /** Get an instance of retrofit for the specified base url*/
    private fun getRetrofit(baseUrl: String) = Retrofit.Builder()
        .client(getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl).build();

    /** Creates a okhttp3 logging interceptor that logs the body of the response*/
    private fun getHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    /**Builds an okhttp3 client*/
    private fun getHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getHttpLoggingInterceptor())
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .build()

    fun getAuthAPI() = getRetrofit(BASE_URL).create(AuthAPI::class.java)
    fun getItemsAPI() = getRetrofit(BASE_URL).create(ItemsAPI::class.java)
}