package com.testdemo.data.repository

import com.google.gson.JsonObject
import com.testdemo.data.api.ApiResource
import com.testdemo.data.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Singleton
class AppRepository @Inject constructor(private val apiService: ApiService) {

    /**
     * for login
     * */
    fun login(jsonObject: JsonObject): Flow<ApiResource<Any?>> = flow {
        runCatching {
            emit(ApiResource.loading(data = null))
            val response = apiService.loginUser(jsonObject)
            if (response.isSuccessful) {
                emit(ApiResource.success(response.body()))
            } else {
                emit(ApiResource.error(response.errorBody(), "Something Went Wrong"))
            }
        }.onFailure {
            emit(ApiResource.error(null, "Server Error"))
        }
    }


    fun getData(auth: String): Flow<ApiResource<Any?>> {
        return flow {
            val response = apiService.getData(auth)
            if (response.isSuccessful) {
                emit(ApiResource.success(response.body()))
            } else {
                emit(ApiResource.error(response.errorBody(), "Something Went Wrong"))
            }
        }
    }

}