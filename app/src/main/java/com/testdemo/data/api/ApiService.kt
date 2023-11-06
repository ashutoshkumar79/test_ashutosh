package com.testdemo.data.api

import com.google.gson.JsonObject
import com.testdemo.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton


@Singleton
interface ApiService {

    @POST("/api/login")
    suspend fun loginUser(
        @Body jsonObject: JsonObject
    ): Response<ApiResponse>

    @POST("/api/v1/user/profile")
    suspend fun getData(
        @Header("Authorization") auth: String
    ): Response<ApiResponse>
}
