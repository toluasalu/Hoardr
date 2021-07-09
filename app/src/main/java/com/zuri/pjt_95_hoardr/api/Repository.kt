package com.zuri.pjt_95_hoardr.api

import android.util.Log
import com.zuri.pjt_95_hoardr.api.models.Item
import com.zuri.pjt_95_hoardr.api.models.User

/**
 * @author Jeffrey Orazulike [chukwudumebiorazulike@gmail.com]
 * Created 09-Jul-21 at 2:11 AM
 */
object Repository {
    val authAPI = RetrofitClient.getAuthAPI()
    val itemsAPI = RetrofitClient.getItemsAPI()

    private fun <T> fetch(data: T, message: String): Result<T>{
        val result = try {
            Result.Success(data, message)
        }catch (e: Exception){
            Result.Failure(e)
        }
        Log.d(javaClass.simpleName, "fetch: $result")
        return result
    }

    suspend fun addItem(item: Item) =
        fetch(itemsAPI.addItem(item), "$item added successfully")
    suspend fun getAllItems() =
        fetch(itemsAPI.getAllItems(), "Items gotten successfully")
    suspend fun getAllItemsDetail() =
        fetch(itemsAPI.getAllItemsDetail(), "Items Detail gotten successfully")
    suspend fun getItemById(id: String) =
        fetch(itemsAPI.getItemById(id), "Item gotten successfully")
    suspend fun getItemDetailById(id: String) =
        fetch(itemsAPI.getItemDetailById(id), "Item Detail gotten successfully")

   suspend fun signup(user: User) =
        fetch(authAPI.signup(user), "$user signed up successfully")
    suspend fun login(user: User) =
        fetch(authAPI.login(user), "$user logged in successfully")
}