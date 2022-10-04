package com.andrest.loginretrofit.retrofit

data class LoginResponse(var token: String): SuccessResponse(token)
