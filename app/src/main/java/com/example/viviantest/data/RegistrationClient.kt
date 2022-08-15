package com.example.viviantest.data

import com.google.gson.annotations.SerializedName

data class RegistrationClient(
    @SerializedName("name")
    val fullName: String = "",
    @SerializedName("phone")
    val phoneNumber: String = "",
    val address: String = ""
)
