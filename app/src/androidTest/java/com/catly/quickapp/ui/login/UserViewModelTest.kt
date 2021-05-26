package com.catly.quickapp.ui.login


import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

internal class UserViewModelTest {

    lateinit var mockVm: UserViewModel

    @Before
    fun setup(){
        mockVm = mockkClass(UserViewModel::class)
    }

    @Test
    fun isPasswordValid() {
        assertTrue(mockVm.isPasswordValid("sadasDSADAS12312d"))
    }
}