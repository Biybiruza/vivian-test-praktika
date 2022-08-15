package com.example.viviantest.data.login

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val employeeId: Int,
    val name: String = "",
    val phone: String = "",
    val role: String = "",
    @SerializedName("branch")
    val branchInfoSignIn: Branch,
    val token: String = ""
)