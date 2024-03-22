package com.example.androidvideokertaus

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

private val retrofitClient = Retrofit.Builder().baseUrl("http://10.0.2.2:8000/api/v1/")
    .addConverterFactory(GsonConverterFactory.create()).build()


val authService = retrofitClient.create(AuthApi::class.java)

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body req: LoginReq): LoginRes

    @GET("auth/account")
    suspend fun getAccount(@Header("Authorization") token: String)

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String)
}


