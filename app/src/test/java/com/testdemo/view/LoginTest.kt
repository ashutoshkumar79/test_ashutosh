package com.testdemo.view

import com.testdemo.utils.InputValidationUtils
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginTest {

    @Test
    fun `empty username returns false`(){
        val result = InputValidationUtils.validLoginInput(
            "",
            "123"
        )
        assertTrue(result)
    }

    @Test
    fun `empty password returns false`() {
        val result = InputValidationUtils.validLoginInput(
            "Rahul",
            ""
        )
        assertTrue(result)
    }

    @Test
    fun `username password returns true`() {
        val result = InputValidationUtils.validLoginInput(
            "Rahul",
            "123"
        )
        assertTrue(result)
    }
}