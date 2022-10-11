package com.andrest.loginretrofit.models

data class User(
    val id: Long,
    val email: String,
    val first_name: String,
    val lastName: String,
    val avatar: String
) {

    fun getFullName(): String = "$first_name $lastName"
}
