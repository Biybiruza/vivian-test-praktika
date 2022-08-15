package com.example.viviantest.data

data class GenericResponse<T>(
    val code: Int,
    val message: String = "",
    val success: Boolean,
    val payload: T
)