package com.zuri.pjt_95_hoardr.api.classes

import com.zuri.pjt_95_hoardr.api.models.Item
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 09-Jul-21 at 1:27 AM
 */
interface ItemsAPI {
    @POST("items")
    suspend fun addItem(@Body item: Item)

    @GET("items")
    suspend fun getAllItems(): List<Item>

    @GET("itemize")
    suspend fun getAllItemsDetail(): List<Item>

    @GET("items/{id}")
    suspend fun getItemById(@Path("id") id:String): Item

    @GET("itemize/{id}")
    suspend fun getItemDetailById(@Path("id") id:String): Item
}