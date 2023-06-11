package com.example.classattendance_androidapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiService {
    @POST("/api/Organization")
    fun signup(@Body data: SignUpData): Call<SignUpData>

}