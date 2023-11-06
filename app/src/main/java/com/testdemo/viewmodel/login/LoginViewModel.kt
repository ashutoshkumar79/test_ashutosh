package com.testdemo.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.testdemo.data.api.ApiResponseStatus
import com.testdemo.data.model.ApiResponse
import com.testdemo.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    val mSuccess = MutableStateFlow(ApiResponse())
    val mErrorMessage = MutableStateFlow(String())

    fun login(jsonObject: JsonObject) {
        viewModelScope.launch {
            appRepository.login(jsonObject).collect {
                if (it.status == ApiResponseStatus.SUCCESS) {
                    mSuccess.value = it.data as ApiResponse
                } else {
                    mErrorMessage.value = it.message.toString()
                }
            }
        }
    }
}