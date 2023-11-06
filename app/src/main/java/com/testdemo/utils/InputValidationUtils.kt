package com.testdemo.utils

object InputValidationUtils {


    fun validLoginInput(
        userName: String,
        password: String
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty()) {
            return false
        }
        return true
    }
}