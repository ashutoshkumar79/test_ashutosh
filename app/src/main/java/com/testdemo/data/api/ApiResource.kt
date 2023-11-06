package com.testdemo.data.api

import com.testdemo.data.api.ApiResponseStatus.ERROR
import com.testdemo.data.api.ApiResponseStatus.SUCCESS
import com.testdemo.data.api.ApiResponseStatus.LOADING


data class ApiResource<out T>(val status: ApiResponseStatus, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): ApiResource<T> = ApiResource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ApiResource<T> =
            ApiResource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): ApiResource<T> = ApiResource(status = LOADING, data = data, message = null)
    }

}