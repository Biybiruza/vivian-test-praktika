package com.example.viviantest.data.login

import com.google.gson.annotations.SerializedName

data class Branch(
    @SerializedName("id")
    val branchId: Int,
    @SerializedName("name")
    val branchName: String,
    @SerializedName("parent_id")
    val parentId: Int,
    val type: Int,
    val warehouse: Boolean
)