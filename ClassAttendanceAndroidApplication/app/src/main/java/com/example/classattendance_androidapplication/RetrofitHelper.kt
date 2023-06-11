package com.example.classattendance_androidapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// This is the RetrofitHelper object, which provides a Retrofit instance for making API requests.
object RetrofitHelper {

    // This function returns a configured Retrofit instance.
    fun getInstance(baseUrl: String): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}