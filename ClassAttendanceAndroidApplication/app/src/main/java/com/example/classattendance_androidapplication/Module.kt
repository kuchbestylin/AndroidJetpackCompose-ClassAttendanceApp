package com.example.classattendance_androidapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// This is the Module entity class, which represents a module in your application.
@Entity
data class Module (
    // We define some variables related to the module.
    var name: String = "",
    var participants: Int = 0,
    var completedClasses: Int = 0,
    var classesPerWeek: Int = 0
){
    // We define the id variable as the primary key for the module entity.
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}