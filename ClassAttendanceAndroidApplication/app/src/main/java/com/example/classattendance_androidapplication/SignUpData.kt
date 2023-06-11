package com.example.classattendance_androidapplication

// This is the SignUpData data class, representing the data required for user sign up.
data class SignUpData(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)