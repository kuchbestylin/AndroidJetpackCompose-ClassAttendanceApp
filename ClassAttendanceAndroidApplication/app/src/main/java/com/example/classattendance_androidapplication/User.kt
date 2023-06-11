package com.example.classattendance_androidapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    var name: String,
    var booleanValue: Boolean,
    var Participants: Int?,
    var daysAWeek: Int?
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}