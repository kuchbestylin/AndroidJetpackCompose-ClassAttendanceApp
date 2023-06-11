package com.example.classattendance_androidapplication

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// This is the interface ApiService, which defines the API endpoints for interacting with user data.
interface ApiService {

    // This is the interface ApiService, which defines the API endpoints for interacting with user data.
    @GET("/api/Lecturer/{id}")
    suspend fun getUserByID(@Path("id") id:Int): Response<JsonObject>

    @GET("/api/Activity")
    suspend fun getActivities(): Response<JsonArray>

    @GET("/api/Organisation")
    suspend fun getOrganisations(): Response<JsonArray>

    // This function is used to update user data by ID.
    @PUT("/api/users/{id}")
    suspend fun updateUser(@Path("id") id:String, @Body body: JsonObject): Response<JsonObject>

    // This function is used to delete user data by ID.
    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") id:String): Response<JsonObject>

    // This function is used to create a new user.
    @POST("/api/users/")
    suspend fun createUser(@Body body: JsonObject): Response<JsonObject>

    // This function is used to create a new user.
    @POST("/api/Activity")
    suspend fun createActivity(@Body body: JsonObject): Response<JsonObject>
}

data class Lecturer(var id: Int?, var name: String, var organizationId: Int?)