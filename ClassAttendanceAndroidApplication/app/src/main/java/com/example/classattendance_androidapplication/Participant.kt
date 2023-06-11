package com.example.classattendance_androidapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// This entity represents a student in the application.
@Entity
data class Student (
    var name: String = "",
    var studentNumber: String = ""
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}