package com.andrest.loginretrofit.retrofit

import com.andrest.login.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Content-type: application/json")
    @POST(Constants.API_PATH + Constants.LOGIN_PATH)
    fun login(@Body data: UserInfo): Call<LoginResponse>


    @POST(Constants.API_PATH + Constants.LOGIN_PATH)
    suspend fun loginUser(@Body data: UserInfo): LoginResponse
}