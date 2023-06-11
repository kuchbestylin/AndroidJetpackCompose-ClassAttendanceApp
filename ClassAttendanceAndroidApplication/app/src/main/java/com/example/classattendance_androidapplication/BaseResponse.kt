package com.example.classattendance_androidapplication

import com.google.gson.annotations.SerializedName

// This is the BaseResponse class, which is a generic data class representing a response with a generic data field.
data class BaseResponse<T> (
    // We use the @SerializedName annotation to specify the JSON field name for the data property.
    @SerializedName("data") var data: T? = null
)