package com.example.classattendance_androidapplication

import androidx.room.*

@Entity(tableName = "course")
data class Course(
    var name: String,
    var booleanValue: Boolean,
    var Participants: Int?,
    var daysAWeek: Int?){
    @PrimaryKey() var id: Int = 0
}

@Dao
interface CourseDao {

    @Query("Select * From courses")
    suspend fun getAll(): List<Course>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(courses: List<Course>)
}